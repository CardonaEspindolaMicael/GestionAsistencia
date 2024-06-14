package com.micael.spring.app.services.moduloMateria.carreraMateriaServicios;

import com.micael.spring.app.DTO.CarreraDto;
import com.micael.spring.app.DTO.CarreraMateriaCreateDto;
import com.micael.spring.app.DTO.CarreraMateriaDto;
import com.micael.spring.app.DTO.ModuloFacultativoDto;
import com.micael.spring.app.entities.moduloMateria.Area;
import com.micael.spring.app.entities.moduloMateria.CarreraMateria;
import com.micael.spring.app.entities.moduloUniversidad.ModuloFacultativo;
import com.micael.spring.app.repositories.materiaRepository.AreaRepository;
import com.micael.spring.app.repositories.materiaRepository.CarreraMateriaRepository;
import com.micael.spring.app.repositories.materiaRepository.MateriaRepository;
import com.micael.spring.app.repositories.moduloUniversidad.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraMateriaServiceJPA implements CarreraMateriaService{
    @Autowired
    CarreraMateriaRepository repository;
    @Autowired
    CarreraRepository carreraRepository;
    @Autowired
    MateriaRepository materiaRepository;
    @Autowired
    AreaRepository areaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CarreraMateria> findAll() {
        return (List<CarreraMateria>) repository.findAll();
    }

    @Override
    public Optional<CarreraMateriaDto> findById(int id) {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<String> save(CarreraMateriaCreateDto carreraMat) {
        return null;
    }

    @Override
    public ResponseEntity<String> update(int id, CarreraMateriaCreateDto carreraMat) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(int id) {
        return null;
    }
}
