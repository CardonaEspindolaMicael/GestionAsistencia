package com.micael.spring.app.controllers.moduloMateria;
import com.micael.spring.app.entities.moduloMateria.Area;
import com.micael.spring.app.services.moduloMateria.areaServicios.AreaService;
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
@RequestMapping("/areas")
public class AreaController {
    @Autowired
    AreaService service;

    @GetMapping
    public List<Area> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable int id){
        Optional<Area> valorOptional= service.findById(id);
        if(valorOptional.isPresent()){
            return ResponseEntity.ok(valorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Area resp, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return  service.save(resp);
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
