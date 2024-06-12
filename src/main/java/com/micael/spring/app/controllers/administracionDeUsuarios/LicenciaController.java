package com.micael.spring.app.controllers.administracionDeUsuarios;

import com.micael.spring.app.DTO.LicenciaDTO;
import com.micael.spring.app.entities.administracionDeUsuarios.Licencia;
import com.micael.spring.app.services.administracionDeUsuarios.licenciaServicios.LicenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
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
        return  ResponseEntity.status(201).body(service.save(licencia));
    }

    @GetMapping
    public List<LicenciaDTO> list(){
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
