package com.micael.spring.app.services.moduloGrupo.materiaGrupoServicios;

import com.micael.spring.app.DTO.CarreraDto;
import com.micael.spring.app.DTO.MateriaGrupoCreateDto;
import com.micael.spring.app.DTO.MateriaGrupoDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface MateriaGrupoService {
    List<MateriaGrupoDto> findAll();

    Optional<MateriaGrupoDto> findById(int id);

    ResponseEntity<String> save(MateriaGrupoCreateDto materiaGrupoCreateDto);

    ResponseEntity<String> update(int id, MateriaGrupoCreateDto materiaGrupoCreateDto);

    ResponseEntity<String> delete(int id);

}
