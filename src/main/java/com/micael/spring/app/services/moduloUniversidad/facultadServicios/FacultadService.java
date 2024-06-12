package com.micael.spring.app.services.moduloUniversidad.facultadServicios;


import com.micael.spring.app.entities.moduloUniversidad.Facultad;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface FacultadService {
    List<Facultad> findAll();

    Facultad save(Facultad facultad);

    ResponseEntity<String> update(int id, Facultad facultad);

    ResponseEntity<String> delete(int id);

    Optional<Facultad> findById(int id);
}
