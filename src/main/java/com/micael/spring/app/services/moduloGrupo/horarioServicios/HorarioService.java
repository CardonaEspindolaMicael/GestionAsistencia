package com.micael.spring.app.services.moduloGrupo.horarioServicios;

import com.micael.spring.app.entities.moduloGrupo.Grupo;
import com.micael.spring.app.entities.moduloGrupo.Horario;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface HorarioService {
    List<Horario> findAll();

    Optional<Horario> findById(int id);

    ResponseEntity<String> save(Horario horario);

    ResponseEntity<String> update(int id, Horario horario);

    ResponseEntity<String> delete(int id);
}
