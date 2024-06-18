package com.micael.spring.app.controllers.administracionDeUsuarios;

import com.micael.spring.app.DTO.UsuarioDto;
import com.micael.spring.app.entities.administracionDeUsuarios.Usuario;
import com.micael.spring.app.services.administracionDeUsuarios.usuarioServicios.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")

public class UsuarioController {
    @Autowired
    private UsuarioService service;
    @GetMapping
    public List<Usuario> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable UUID id){
        Optional<Usuario> usuarioOptional= service.findById(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/holaMundo")
    public String mensaje(){
        return  "Hola mundo";
    }
    @GetMapping("/byEmail/{email}")
    public ResponseEntity<?> viewEmail(@PathVariable String email){
        System.out.println(email);
        Optional<Usuario> usuarioOptional= service.findByEmail(email);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioDto usuario, BindingResult result){
      if(result.hasErrors()){
       return validation(result);
      }
     return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> update(@Valid @RequestBody UsuarioDto usuario, BindingResult result,@PathVariable UUID id){
        if(result.hasErrors()){
            return validation(result);
        }
        Optional<Usuario> response=service.update(id,usuario);
        if (response.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(response.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        Optional<Usuario> usuarioOptional= service.delete(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors= new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errors.put(err.getField(),"El campo "+err.getField()+" "+err.getDefaultMessage());
        });
     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


}
