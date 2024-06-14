package com.micael.spring.app.services.moduloMateria.areaServicios;

import com.micael.spring.app.entities.moduloMateria.Area;
import com.micael.spring.app.entities.moduloMateria.Materia;
import com.micael.spring.app.repositories.materiaRepository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaServiceJPA implements AreaService{
    @Autowired
    AreaRepository repository;

    @Override
    public List<Area> findAll() {
        return (List<Area>) repository.findAll();
    }

    @Override
    public Optional<Area> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public ResponseEntity<String> save(Area area) {
        try {
            repository.save(area);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrio algo inesperado", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Creado exitosamente");
    }

    @Override
    public ResponseEntity<String> update(int id, Area area) {
        return null;
    }

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
