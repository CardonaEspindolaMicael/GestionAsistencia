package com.micael.spring.app.services.moduloUniversidad.aulaServicios;

import com.micael.spring.app.DTO.CarreraDto;
import com.micael.spring.app.entities.moduloUniversidad.Aula;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AulaService {
    List<Aula> findAll();

    Optional<Aula> findById(int id);

    ResponseEntity<String> save(Aula aula);

    ResponseEntity<String> update(int id, Aula aula);

    ResponseEntity<String> delete(int id);
}
