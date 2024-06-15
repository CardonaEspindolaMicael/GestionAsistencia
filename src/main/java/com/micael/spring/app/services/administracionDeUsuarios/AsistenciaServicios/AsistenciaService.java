package com.micael.spring.app.services.administracionDeUsuarios.AsistenciaServicios;

import com.micael.spring.app.DTO.AsistenciaDto;
import com.micael.spring.app.DTO.AsistenciaMarcarDto;
import com.micael.spring.app.entities.administracionDeUsuarios.Asistencia;
import com.micael.spring.app.entities.moduloGrupo.Grupo;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AsistenciaService {
    List<AsistenciaDto> findAll();

    Optional<AsistenciaDto> findById(int id);

    ResponseEntity<String> save(AsistenciaDto asistencia);

    ResponseEntity<String> update(int id, AsistenciaDto asistenciaDto);

    ResponseEntity<String> delete(int id);

    ResponseEntity<String> actualizarAsistencia(UUID id_usuario);

}
