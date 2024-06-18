package com.micael.spring.app.controllers.moduloGrupo;

import com.micael.spring.app.DTO.MateriaGrupoCreateDto;
import com.micael.spring.app.DTO.MateriaGrupoDto;
import com.micael.spring.app.services.moduloGrupo.materiaGrupoServicios.MateriaGrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/materiaGrupo")
public class MateriaGrupoController {
    @Autowired
    MateriaGrupoService service;
    @GetMapping
    public List<MateriaGrupoDto> list(){
        return service.findAll();
    }


    @GetMapping("/getByUser/{id}")
    public List<MateriaGrupoDto> listAllId(@PathVariable UUID id){
        return service.findAllByIdUsuario(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable int id){
        Optional<MateriaGrupoDto> valorOptional= service.findById(id);
        if(valorOptional.isPresent()){
            return ResponseEntity.ok(valorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object>  create(@Valid @RequestBody MateriaGrupoCreateDto resp, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return  service.save(resp);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<?> update(@Valid @RequestBody MateriaGrupoCreateDto usuario, BindingResult result, @PathVariable int id){
        if(result.hasErrors()){
            return validation(result);
        }

        return service.update(id,usuario);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        ResponseEntity<String> responseOptional= service.delete(id);
        if(responseOptional.hasBody()){
            return responseOptional;
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Object>  validation(BindingResult result) {
        Map<String,String> errors= new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errors.put(err.getField(),"El campo "+err.getField()+" "+err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
