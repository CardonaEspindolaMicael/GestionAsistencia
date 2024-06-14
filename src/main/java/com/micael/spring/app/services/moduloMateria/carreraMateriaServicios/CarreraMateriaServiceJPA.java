package com.micael.spring.app.services.moduloMateria.carreraMateriaServicios;
import com.micael.spring.app.DTO.*;
import com.micael.spring.app.entities.moduloMateria.CarreraMateria;
import com.micael.spring.app.entities.moduloMateria.Materia;
import com.micael.spring.app.entities.moduloUniversidad.Carrera;
import com.micael.spring.app.repositories.materiaRepository.CarreraMateriaRepository;
import com.micael.spring.app.repositories.materiaRepository.MateriaRepository;
import com.micael.spring.app.repositories.moduloUniversidad.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional(readOnly = true)
    @Override
    public List<CarreraMateriaDto> findAll() {
        List<CarreraMateria> licencias = (List<CarreraMateria>) repository.findAll();
        List<CarreraMateriaDto> licenciaDTOList = new ArrayList<>();

        if (!licencias.isEmpty()) {

            for (CarreraMateria licencia : licencias) {
                Carrera carrera = carreraRepository.findById(licencia.getCarrera().getId()).orElseThrow();
                Materia materia = materiaRepository.findById(licencia.getMateria().getId()).orElseThrow();
                licenciaDTOList.add(
                        new CarreraMateriaDto(
                                licencia.getId(),
                                new CarreraDto(
                                        carrera.getId(),
                                        carrera.getNombre(),
                                        carrera.getAnios(), carrera.getFacultad().getId()
                                ),
                                new MateriaDto(
                                        materia.getId(),
                                        materia.getNombre(),
                                        materia.getSiglas(),
                                        materia.getNivel(),
                                        materia.getArea().getId()
                                )
                        )
                );


            }

        }
        return licenciaDTOList;
    }
@Transactional(readOnly = true)
    @Override
    public Optional<CarreraMateriaDto> findById(int id) {
        Optional<CarreraMateria> licencia = repository.findById(id);
        if(licencia.isEmpty()){
            return Optional.of(null);
        }
        CarreraMateria carreraMat=licencia.orElseThrow();

        Carrera carrera = carreraRepository.findById(carreraMat.getCarrera().getId()).orElseThrow();
        Materia materia = materiaRepository.findById(carreraMat.getMateria().getId()).orElseThrow();
        return Optional.of(new CarreraMateriaDto(
                carreraMat.getId(),
                new CarreraDto(
                        carrera.getId(),
                        carrera.getNombre(),
                        carrera.getAnios(), carrera.getFacultad().getId()
                ),
                new MateriaDto(
                        materia.getId(),
                        materia.getNombre(),
                        materia.getSiglas(),
                        materia.getNivel(),
                        materia.getArea().getId()
                )
        ));

    }

    @Override
    public ResponseEntity<String> save(CarreraMateriaCreateDto carreraMat) {
        Carrera carrera = carreraRepository.findById(carreraMat.getId_carrera()).orElseThrow();
        Materia materia = materiaRepository.findById(carreraMat.getId_materia()).orElseThrow();
        repository.save(new CarreraMateria(carreraMat.getId(),carrera,materia));
        return ResponseEntity.ok("Creado exitosamente");

    }

    @Override
    public ResponseEntity<String> update(int id, CarreraMateriaCreateDto carreraMat) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(int id) {
        Optional<CarreraMateria> porID= repository.findById(id);
        if(porID.isEmpty()){
            return new ResponseEntity<>("No encontrado", HttpStatus.NOT_FOUND);
        }
        porID.ifPresent(user ->{
            repository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito", HttpStatus.ACCEPTED);
    }

    @Override
    public List<CarreraMateriaDto> findByCarreraId(Integer carreraId) {
        List<CarreraMateria> licencias = (List<CarreraMateria>) repository.findByCarreraId(carreraId);
        List<CarreraMateriaDto> licenciaDTOList = new ArrayList<>();

        if (!licencias.isEmpty()) {

            for (CarreraMateria licencia : licencias) {
                Carrera carrera = carreraRepository.findById(licencia.getCarrera().getId()).orElseThrow();
                Materia materia = materiaRepository.findById(licencia.getMateria().getId()).orElseThrow();
                licenciaDTOList.add(
                        new CarreraMateriaDto(
                                licencia.getId(),
                                new CarreraDto(
                                        carrera.getId(),
                                        carrera.getNombre(),
                                        carrera.getAnios(), carrera.getFacultad().getId()
                                ),
                                new MateriaDto(
                                        materia.getId(),
                                        materia.getNombre(),
                                        materia.getSiglas(),
                                        materia.getNivel(),
                                        materia.getArea().getId()
                                )
                        )
                );


            }

        }
        return licenciaDTOList;
    }
}

