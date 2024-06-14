package com.micael.spring.app.services.moduloMateria.carreraMateriaServicios;

import com.micael.spring.app.DTO.CarreraMateriaCreateDto;
import com.micael.spring.app.DTO.CarreraMateriaDto;
import com.micael.spring.app.entities.moduloMateria.Area;
import com.micael.spring.app.entities.moduloMateria.CarreraMateria;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CarreraMateriaService {

    List<CarreraMateriaDto> findAll();

    Optional<CarreraMateriaDto> findById(int id);

    ResponseEntity<String> save(CarreraMateriaCreateDto carreraMat);

    ResponseEntity<String> update(int id, CarreraMateriaCreateDto carreraMat);

    ResponseEntity<String> delete(int id);

    List<CarreraMateriaDto> findByCarreraId(@Param("carreraId") Integer carreraId);
}
