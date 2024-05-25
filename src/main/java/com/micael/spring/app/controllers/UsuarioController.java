package com.micael.spring.app.controllers;

import com.micael.spring.app.entities.Usuario;
import com.micael.spring.app.services.usuarioServicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
     return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Usuario> update(@PathVariable UUID id, @RequestBody Usuario usuario){
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


}
