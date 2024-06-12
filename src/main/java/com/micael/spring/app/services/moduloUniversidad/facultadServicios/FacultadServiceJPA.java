package com.micael.spring.app.services.moduloUniversidad.facultadServicios;
import com.micael.spring.app.entities.moduloUniversidad.Facultad;
import com.micael.spring.app.repositories.moduloUniversidad.FacultadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacultadServiceJPA implements FacultadService {
    @Autowired
    FacultadRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Facultad> findAll() {
        return (List<Facultad>) repository.findAll();
    }

    @Transactional
    @Override
    public Facultad save(Facultad facultad) {
        return repository.save(facultad);
    }

    @Transactional
    @Override
    public ResponseEntity<String> update(int id, Facultad facultad) {
        try {
            Optional<Facultad> facultadPorID= repository.findById(id);
            if(facultadPorID.isPresent()){
                Facultad facultadDb=facultadPorID.orElseThrow();
                facultadDb.setNombre(facultad.getNombre());
                facultadDb.setSigla(facultad.getSigla());
                return new ResponseEntity<>("Actualizado con exito",HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Algo salio mal" , HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>("Vacio" , HttpStatus.NOT_FOUND);    }

    @Transactional
    @Override
    public ResponseEntity<String> delete(int id) {
        Optional<Facultad> facultadPorID= repository.findById(id);
        facultadPorID.ifPresent(user ->{
            repository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito",HttpStatus.ACCEPTED);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Facultad> findById(int id) {
        return repository.findById(id);
    }
}
