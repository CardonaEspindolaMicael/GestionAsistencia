package com.micael.spring.app.controllers.administracionDeUsuarios;
import com.micael.spring.app.DTO.AsistenciaDto;
import com.micael.spring.app.services.administracionDeUsuarios.AsistenciaServicios.AsistenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/asistencias")
public class AsistenciaController {
    @Autowired
    AsistenciaService service;
    @GetMapping
    public List<AsistenciaDto> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable int id){
        Optional<AsistenciaDto> valorOptional= service.findById(id);
        if(valorOptional.isPresent()){
            return ResponseEntity.ok(valorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }




    @GetMapping("/usuarioId/{id}")
    public List<AsistenciaDto> view(@PathVariable UUID id){

        return   service.findAsistenciaPorId(id);
    }


    @GetMapping("/asistenciasUsuario/{id}/{fecha}")
    public List<AsistenciaDto> view(@PathVariable UUID id,@PathVariable LocalDate fecha){

        return   service.findPorDateAndUsuario_Id(id,fecha);
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AsistenciaDto resp, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return  service.save(resp);
    }

    @PutMapping("/marcar/{id}")
    public  ResponseEntity<Object> updateAsistence( @PathVariable UUID id){

        ResponseEntity<Object> response= null;
        try {
            return service.actualizarAsistencia(id);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", "Un error a ocurrido"));
        }

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
