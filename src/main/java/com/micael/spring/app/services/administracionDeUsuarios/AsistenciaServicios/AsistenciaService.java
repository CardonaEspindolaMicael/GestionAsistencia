package com.micael.spring.app.services.administracionDeUsuarios.AsistenciaServicios;

import com.micael.spring.app.DTO.AsistenciaDto;
import com.micael.spring.app.entities.administracionDeUsuarios.Asistencia;
import com.micael.spring.app.entities.moduloGrupo.Grupo;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AsistenciaService {
    List<AsistenciaDto> findAll();

    Optional<AsistenciaDto> findById(int id);

    ResponseEntity<String> save(AsistenciaDto asistencia);

    ResponseEntity<String> update(int id, AsistenciaDto asistencia);

    ResponseEntity<String> delete(int id);
}
