package com.micael.spring.app.controllers.administracionDeUsuarios;

import com.micael.spring.app.DTO.LicenciaDTO;
import com.micael.spring.app.entities.administracionDeUsuarios.Licencia;
import com.micael.spring.app.entities.administracionDeUsuarios.Usuario;
import com.micael.spring.app.services.administracionDeUsuarios.licenciaServicios.LicenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/licencias")
public class LicenciaController {
    @Autowired
    private LicenciaService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody LicenciaDTO licencia, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return  service.save(licencia);
    }

    @GetMapping
    public List<LicenciaDTO> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable int id){
        Optional<LicenciaDTO> usuarioOptional= service.findById(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> update(@Valid @RequestBody LicenciaDTO usuario, BindingResult result, @PathVariable int id){
        if(result.hasErrors()){
            return validation(result);
        }
        ResponseEntity<Object> response=service.update(id,usuario);
        if (response.hasBody()){
            return response;
        }
        return ResponseEntity.notFound().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        ResponseEntity<Object> usuarioOptional= service.delete(id);
        if(usuarioOptional.hasBody()){
            return usuarioOptional;
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
