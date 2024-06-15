package com.micael.spring.app.services.moduloUniversidad.aulaServicios;

import com.micael.spring.app.entities.moduloUniversidad.Aula;
import com.micael.spring.app.repositories.moduloUniversidad.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AulaServiceJPA implements AulaService{
    @Autowired
    AulaRepository aulaRepository;
    @Override
    public List<Aula> findAll() {
        return (List<Aula>) aulaRepository.findAll();
    }

    @Override
    public Optional<Aula> findById(int id) {
        return aulaRepository.findById(id);
    }

    @Override
    public ResponseEntity<String> save(Aula aula) {
        try {
            aulaRepository.save(aula);
        } catch (Exception e) {
            return ResponseEntity.ok("Error inesperado");
        }
        return ResponseEntity.ok("Creado con exito");
    }

    @Override
    public ResponseEntity<String> update(int id, Aula aula) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(int id) {
        Optional<Aula> usuarioPorID= aulaRepository.findById(id);
        usuarioPorID.ifPresent(user ->{
            aulaRepository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito", HttpStatus.ACCEPTED);
    }
}
