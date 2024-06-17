package com.micael.spring.app.services.moduloMateria.areaServicios;

import com.micael.spring.app.entities.moduloMateria.Area;
import com.micael.spring.app.entities.moduloMateria.Materia;
import com.micael.spring.app.repositories.materiaRepository.AreaRepository;
import com.micael.spring.app.repositories.materiaRepository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AreaServiceJPA implements AreaService{
    @Autowired
    AreaRepository repository;
    @Autowired
    MateriaRepository materiaRepository;


    @Transactional
    @Override
    public List<Area> findAll() {

        List<Area> areas= (List<Area>) repository.findAll();
        List<Area> dtoList=new ArrayList<>();
        if(!areas.isEmpty()){
            for(Area area:areas){
                List<Materia> materiaList=materiaRepository.findMateriasByAreaId(area.getId());
                List<Materia> materiasActualizadas=new ArrayList<>();
                for(Materia materia:materiaList){
                 materiasActualizadas.add(
                         new Materia(
                                 materia.getId(),
                                 materia.getNombre(),
                                 materia.getSiglas(),
                                 materia.getNivel(),
                                 null,null
                         ));
                }
                dtoList.add(
                        new Area(
                                area.getId(),
                                area.getNombre(),
                                materiasActualizadas
                        )
                );
            }
        }
        return dtoList;
    }
    @Transactional
    @Override
    public Optional<Area> findById(int id) {
        Area area= repository.findById(id).orElseThrow();

                List<Materia> materiaList=materiaRepository.findMateriasByAreaId(area.getId());
                List<Materia> materiasActualizadas=new ArrayList<>();
                for(Materia materia:materiaList){
                    materiasActualizadas.add(
                            new Materia(
                                    materia.getId(),
                                    materia.getNombre(),
                                    materia.getSiglas(),
                                    materia.getNivel(),
                                    null,null
                            ));
                }

                       return Optional.of(new Area(
                               area.getId(),
                               area.getNombre(),
                               materiasActualizadas

                       ));


    }
    @Transactional
    @Override
    public ResponseEntity<String> save(Area area) {
        try {
            repository.save(area);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrio algo inesperado", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Creado exitosamente");
    }
    @Transactional
    @Override
    public ResponseEntity<String> update(int id, Area area) {
        return ResponseEntity.ok("Proximamente");
    }
    @Transactional
    @Override
    public ResponseEntity<String> delete(int id) {
        Optional<Area> porID= repository.findById(id);
        if(porID.isEmpty()){
            return new ResponseEntity<>("No encontrado", HttpStatus.NOT_FOUND);
        }
        porID.ifPresent(user ->{
            repository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito", HttpStatus.ACCEPTED);
    }
}
