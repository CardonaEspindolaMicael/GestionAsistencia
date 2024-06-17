package com.micael.spring.app.controllers.moduloMateria;

import com.micael.spring.app.DTO.CarreraMateriaCreateDto;
import com.micael.spring.app.DTO.CarreraMateriaDto;
import com.micael.spring.app.DTO.MateriaDto;
import com.micael.spring.app.entities.moduloMateria.Area;
import com.micael.spring.app.entities.moduloMateria.CarreraMateria;
import com.micael.spring.app.entities.moduloMateria.Materia;
import com.micael.spring.app.services.moduloMateria.carreraMateriaServicios.CarreraMateriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carreraMaterias")
public class CarreraMateriaController  {
    @Autowired
    CarreraMateriaService service;

    @GetMapping
    public List<CarreraMateriaDto> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable int id){
        Optional<CarreraMateriaDto> valorOptional= service.findById(id);
        if(valorOptional.isPresent()){
            return ResponseEntity.ok(valorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/carrera/{carreraId}")
    public List<CarreraMateriaDto> findByCarreraId(@PathVariable Integer carreraId){
        List<CarreraMateriaDto> valorOptional = service.findByCarreraId(carreraId);
        if(!valorOptional.isEmpty()){
            return valorOptional;
        }
        return new ArrayList<>();
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CarreraMateriaCreateDto resp, BindingResult result){
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
