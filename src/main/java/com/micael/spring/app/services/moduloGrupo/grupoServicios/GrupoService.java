package com.micael.spring.app.services.moduloGrupo.grupoServicios;

import com.micael.spring.app.entities.moduloGrupo.Grupo;
import com.micael.spring.app.entities.moduloUniversidad.Aula;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface GrupoService {
    List<Grupo> findAll();

    Optional<Grupo> findById(int id);

    ResponseEntity<String> save(Grupo grupo);

    ResponseEntity<String> update(int id, Grupo grupo);

    ResponseEntity<String> delete(int id);
}
