package com.micael.spring.app.controllers.moduloUniversidad;


import com.micael.spring.app.DTO.DocenteFacultadDto;
import com.micael.spring.app.entities.moduloUniversidad.Facultad;
import com.micael.spring.app.services.moduloUniversidad.docenteFacultad.DocenteFacultadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/docenteFacultades")
public class DocenteFacultadController {
    @Autowired
    DocenteFacultadService service;

    @GetMapping
    public List<DocenteFacultadDto> list(){
        return service.findAll();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors= new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errors.put(err.getField(),"El campo "+err.getField()+" "+err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
