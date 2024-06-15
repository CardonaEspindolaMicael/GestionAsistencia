package com.micael.spring.app.services.moduloGrupo.grupoServicios;

import com.micael.spring.app.entities.moduloGrupo.Grupo;
import com.micael.spring.app.repositories.moduloGrupo.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoServiceJPA implements GrupoService{
    @Autowired
    GrupoRepository repository;
    @Transactional(readOnly = true)
    @Override
    public List<Grupo> findAll() {
        return (List<Grupo>) repository.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Grupo> findById(int id) {
        return repository.findById(id);
    }
    @Transactional
    @Override
    public ResponseEntity<String> save(Grupo grupo) {
        try {
            repository.save(grupo);
        } catch (Exception e) {
            return ResponseEntity.ok("Error inesperado");
        }
        return ResponseEntity.ok("Creado con exito");
    }
    @Transactional
    @Override
    public ResponseEntity<String> update(int id, Grupo grupo) {
        return null;
    }
    @Transactional
    @Override
    public ResponseEntity<String> delete(int id) {
        Optional<Grupo> usuarioPorID= repository.findById(id);
        usuarioPorID.ifPresent(user ->{
            repository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito", HttpStatus.ACCEPTED);
    }
}
