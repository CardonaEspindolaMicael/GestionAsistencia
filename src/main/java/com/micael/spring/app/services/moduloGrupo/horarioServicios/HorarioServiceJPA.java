package com.micael.spring.app.services.moduloGrupo.horarioServicios;
import com.micael.spring.app.entities.moduloGrupo.Horario;
import com.micael.spring.app.repositories.moduloGrupo.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioServiceJPA implements HorarioService{
    @Autowired
    HorarioRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Horario> findAll() {
        return (List<Horario>) repository.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Horario> findById(int id) {
        return repository.findById(id);
    }
    @Transactional
    @Override
    public ResponseEntity<String> save(Horario horario) {
        try {
            repository.save(horario);
        } catch (Exception e) {
            return ResponseEntity.ok("Error inesperado");
        }
        return ResponseEntity.ok("Creado con exito");
    }
    @Transactional
    @Override
    public ResponseEntity<String> update(int id, Horario horario) {
        return null;
    }
    @Transactional
    @Override
    public ResponseEntity<String> delete(int id) {
        Optional<Horario> usuarioPorID= repository.findById(id);
        usuarioPorID.ifPresent(user ->{
            repository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito", HttpStatus.ACCEPTED);
    }
}
