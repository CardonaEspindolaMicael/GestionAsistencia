package com.micael.spring.app.services.moduloMateria.materiaServicios;

import com.micael.spring.app.DTO.CarreraDto;
import com.micael.spring.app.DTO.MateriaDto;
import com.micael.spring.app.entities.moduloMateria.Area;
import com.micael.spring.app.entities.moduloMateria.Materia;
import com.micael.spring.app.entities.moduloUniversidad.Carrera;
import com.micael.spring.app.entities.moduloUniversidad.Facultad;
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
public class MateriaServiceJPA implements MateriaService {
    @Autowired
    MateriaRepository repository;
    @Autowired
    AreaRepository areaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Materia> findAll() {
        List<Materia> materias= (List<Materia>) repository.findAll();
        List<Materia> dtoList=new ArrayList<>();
        if(!materias.isEmpty()){
            for(Materia materia:materias){
             Area area=areaRepository.findById(materia.getArea().getId()).orElseThrow();
                dtoList.add(
                        new Materia(
                                materia.getId(),
                                materia.getNombre(),
                                materia.getSiglas(),
                                materia.getNivel(),
                                new Area(area.getId(),area.getNombre(),null),null
                        )
                        );
            }
        }
        return dtoList;
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Materia> findById(int id) {
        Materia materia=repository.findById(id).orElseThrow();
        Area area=areaRepository.findById(materia.getArea().getId()).orElseThrow();
        Optional<Materia> materiaOptional= Optional.of(new Materia(
                materia.getId(),
                materia.getNombre(),
                materia.getSiglas(),
                materia.getNivel(),
                new Area(area.getId(), area.getNombre(), null), null
        ));
        return  materiaOptional;
    }
    @Transactional
    @Override
    public ResponseEntity<String> save(MateriaDto materiaDto) {
        Area area = areaRepository.findById(materiaDto.getId_area())
                .orElseThrow(() -> new RuntimeException("no encontrado"));
        Materia materia = new Materia(materiaDto.getId(),
                materiaDto.getNombre(), materiaDto.getSiglas(), materiaDto.getNivel(), area,null);
        repository.save(materia);
        return ResponseEntity.ok("Creado exitosamente");
    }
    @Transactional
    @Override
    public ResponseEntity<String> update(int id, MateriaDto materiaDto) {
        return ResponseEntity.ok("Proximamente");
    }
    @Transactional
    @Override
    public ResponseEntity<String> delete(int id) {
        Optional<Materia> porID= repository.findById(id);
        if(porID.isEmpty()){
            return new ResponseEntity<>("No encontrado", HttpStatus.NOT_FOUND);
        }
        porID.ifPresent(user ->{
            repository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito", HttpStatus.ACCEPTED);
    }
}
