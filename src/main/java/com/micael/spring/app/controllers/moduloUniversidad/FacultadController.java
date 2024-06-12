package com.micael.spring.app.controllers.moduloUniversidad;

import com.micael.spring.app.DTO.LicenciaDTO;
import com.micael.spring.app.entities.moduloUniversidad.Facultad;
import com.micael.spring.app.services.moduloUniversidad.facultadServicios.FacultadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/facultades")
public class FacultadController {
    @Autowired
    private FacultadService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Facultad facultad, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return  ResponseEntity.status(201).body(service.save(facultad));
    }

    @GetMapping
    public List<Facultad> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable int id){
        Optional<Facultad> facultadOptional= service.findById(id);
        if(facultadOptional.isPresent()){
            return ResponseEntity.ok(facultadOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> update(@Valid @RequestBody Facultad facultad, BindingResult result, @PathVariable int id){
        if(result.hasErrors()){
            return validation(result);
        }
        ResponseEntity<String> response=service.update(id,facultad);
        if (response.hasBody()){
            return response;
        }
        return ResponseEntity.notFound().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        ResponseEntity<String> facultadOptional= service.delete(id);
        if(facultadOptional.hasBody()){
            return facultadOptional;
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
