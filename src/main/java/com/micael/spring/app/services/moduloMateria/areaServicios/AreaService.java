package com.micael.spring.app.services.moduloMateria.areaServicios;

import com.micael.spring.app.DTO.MateriaDto;
import com.micael.spring.app.entities.moduloMateria.Area;
import com.micael.spring.app.entities.moduloMateria.Materia;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AreaService {

    List<Area> findAll();

    Optional<Area> findById(int id);

    ResponseEntity<String> save(Area area);

    ResponseEntity<String> update(int id, Area area);

    ResponseEntity<String> delete(int id);

}
