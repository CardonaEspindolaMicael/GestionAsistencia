package com.micael.spring.app.controllers.moduloUniversidad;

import com.micael.spring.app.DTO.DocenteEnsenaCreateDto;
import com.micael.spring.app.DTO.DocenteEnsenaDto;
import com.micael.spring.app.DTO.DocenteFacultadCreateDto;
import com.micael.spring.app.DTO.DocenteFacultadDto;
import com.micael.spring.app.services.moduloUniversidad.docenteEnsenaServicios.DocenteEnsenaService;
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
@RequestMapping("/docenteEnsena")
public class DocenteEnsenaController {
    @Autowired
    DocenteEnsenaService service;
    @GetMapping
    public List<DocenteEnsenaDto> list(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DocenteEnsenaCreateDto licencia, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return  ResponseEntity.status(201).body(service.save(licencia));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable int id){
        Optional<DocenteEnsenaDto> responseOptional= service.findById(id);
        if(responseOptional.isPresent()){
            return ResponseEntity.ok(responseOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        ResponseEntity<String> responseOptional= service.delete(id);
        if(responseOptional.hasBody()){
            return responseOptional;
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
