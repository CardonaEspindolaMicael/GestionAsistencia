package com.micael.spring.app.services.moduloMateria.materiaServicios;

import com.micael.spring.app.DTO.CarreraDto;
import com.micael.spring.app.DTO.MateriaDto;
import com.micael.spring.app.entities.moduloMateria.Materia;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface MateriaService {

    List<Materia> findAll();

    Optional<Materia> findById(int id);

    ResponseEntity<String> save(MateriaDto materia);

    ResponseEntity<String> update(int id, MateriaDto materia);

    ResponseEntity<String> delete(int id);
}
