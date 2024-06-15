package com.micael.spring.app.services.moduloUniversidad.docenteEnsenaServicios;

import com.micael.spring.app.DTO.DocenteEnsenaCreateDto;
import com.micael.spring.app.DTO.DocenteEnsenaDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DocenteEnsenaService {
    List<DocenteEnsenaDto> findAll();

    Optional<DocenteEnsenaDto> findById(int id);

    ResponseEntity<String> save(DocenteEnsenaCreateDto docenteEnsena);

    ResponseEntity<String> update(int id, DocenteEnsenaCreateDto docenteEnsena);

    ResponseEntity<String> delete(int id);

}
