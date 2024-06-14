package com.micael.spring.app.services.moduloUniversidad.moduloFacultativoServicios;

import com.micael.spring.app.DTO.CarreraDto;
import com.micael.spring.app.DTO.ModuloFacultativoDto;
import com.micael.spring.app.entities.moduloUniversidad.Carrera;
import com.micael.spring.app.entities.moduloUniversidad.Facultad;
import com.micael.spring.app.entities.moduloUniversidad.ModuloFacultativo;
import com.micael.spring.app.repositories.moduloUniversidad.FacultadRepository;
import com.micael.spring.app.repositories.moduloUniversidad.ModuloFacultativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ModuloFacultativoServiceJPA implements ModuloFacultativoService{
    @Autowired
    ModuloFacultativoRepository repository;
    @Autowired
    FacultadRepository facultadRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ModuloFacultativoDto> findAll() {
        List<ModuloFacultativo> licencias= (List<ModuloFacultativo>) repository.findAll();
        List<ModuloFacultativoDto> licenciaDTOList=new ArrayList<>();
        if(!licencias.isEmpty()){
            for(ModuloFacultativo licencia:licencias){
                licenciaDTOList.add(
                        new ModuloFacultativoDto(
                                licencia.getId()
                                ,licencia.getNombre(),
                                licencia.getNumero(),
                                licencia.getFacultad().getId()
                        ));
            }
        }
        return licenciaDTOList;
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<ModuloFacultativoDto> findById(int id) {
        Optional<ModuloFacultativo> licencia= repository.findById(id);

        if(licencia.isPresent()){
            ModuloFacultativo licenciaResponse=licencia.orElseThrow(() -> new RuntimeException("Licencia no encontrada"));
            return Optional.of(new ModuloFacultativoDto(licenciaResponse.getId(), licenciaResponse.getNombre(), licenciaResponse.getNumero(), licenciaResponse.getFacultad().getId()));

        }
        return Optional.empty();
    }
    @Transactional
    @Override
    public ResponseEntity<String> save(ModuloFacultativoDto modulo) {
        Facultad facultad = facultadRepository.findById(modulo.getId_facultad())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        ModuloFacultativo licencia = new ModuloFacultativo(modulo.getId(),
                modulo.getNombre(), modulo.getNumero(),facultad,null);
        ModuloFacultativo response=repository.save(licencia);
        return ResponseEntity.ok("Creado exitosamente");
    }
    @Transactional
    @Override
    public ResponseEntity<String> update(int id, ModuloFacultativoDto modulo) {
        return null;
    }
    @Transactional
    @Override
    public ResponseEntity<String> delete(int id) {
        Optional<ModuloFacultativo> usuarioPorID= repository.findById(id);
        usuarioPorID.ifPresent(user ->{
            repository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito", HttpStatus.ACCEPTED);
    }
}
