package com.micael.spring.app.services.moduloMateria.moduloEnsenanzaServicios;

import com.micael.spring.app.DTO.DocenteEnsenaDto;
import com.micael.spring.app.DTO.DocenteFacultadDto;
import com.micael.spring.app.DTO.MateriaDto;
import com.micael.spring.app.DTO.ModuloEnsenanzaCreateDto;
import com.micael.spring.app.entities.moduloMateria.Materia;
import com.micael.spring.app.entities.moduloMateria.ModuloEnsenanza;
import com.micael.spring.app.entities.moduloUniversidad.DocenteEnsena;
import com.micael.spring.app.entities.moduloUniversidad.DocenteFacultad;
import com.micael.spring.app.entities.moduloUniversidad.ModuloFacultativo;
import com.micael.spring.app.repositories.materiaRepository.ModuloEnsenazaRepository;
import com.micael.spring.app.repositories.moduloUniversidad.DocenteEnsenaRepository;
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
public class ModuloEnsenanzaServiceJPA implements ModuloEnsenanzaService {

    @Autowired
    ModuloEnsenazaRepository repository;
    @Autowired
    DocenteEnsenaRepository docenteEnsenaRepository;
    @Autowired
    ModuloFacultativoRepository moduloFacultativoRepository;
    @Transactional(readOnly = true)
    @Override
    public List<ModuloEnsenanzaCreateDto> findAll() {
        List<ModuloEnsenanza> moduloEnsenanzaList= (List<ModuloEnsenanza>) repository.findAll();
        List<ModuloEnsenanzaCreateDto> licenciaDTOList=new ArrayList<>();

        if(!moduloEnsenanzaList.isEmpty()){

            for(ModuloEnsenanza moduloEnsenanza:moduloEnsenanzaList){
                DocenteEnsena docenteEnsena= docenteEnsenaRepository.findById(moduloEnsenanza.getDocenteEnsena().getId()).orElseThrow();
                ModuloFacultativo moduloFacultativo= moduloFacultativoRepository.findById(moduloEnsenanza.getModuloFacultativo().getId()).orElseThrow();
                licenciaDTOList.add(
                        new ModuloEnsenanzaCreateDto(
                                moduloEnsenanza.getId(),
                                moduloEnsenanza.getId(),
                                docenteEnsena.getId()
                        )
                );
            }
        }
        return licenciaDTOList;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ModuloEnsenanzaCreateDto> findById(int id) {
        ModuloEnsenanza moduloEnsenanza=  repository.findById(id).orElseThrow();
        DocenteEnsena docenteEnsena= docenteEnsenaRepository.findById(moduloEnsenanza.getDocenteEnsena().getId()).orElseThrow();
        ModuloFacultativo moduloFacultativo= moduloFacultativoRepository.findById(moduloEnsenanza.getModuloFacultativo().getId()).orElseThrow();

        return Optional.of(new ModuloEnsenanzaCreateDto(id,docenteEnsena.getId(),moduloFacultativo.getId()));

    }

    @Transactional
    @Override
    public ResponseEntity<String> save(ModuloEnsenanzaCreateDto moduloEnsenanza) {
        DocenteEnsena docenteEnsena= docenteEnsenaRepository.findById(moduloEnsenanza.getId_docenteEnsena()).orElseThrow();
        ModuloFacultativo moduloFacultativo= moduloFacultativoRepository.findById(moduloEnsenanza.getId_modulo()).orElseThrow();
        repository.save(new ModuloEnsenanza(moduloEnsenanza.getId(),moduloFacultativo,docenteEnsena));
        return ResponseEntity.ok("creado correctamente");
    }
    @Transactional
    @Override
    public ResponseEntity<String> update(int id, ModuloEnsenanzaCreateDto moduloEnsenanza) {
        return ResponseEntity.ok("Proximamente");
    }

    @Transactional
    @Override
    public ResponseEntity<String> delete(int id) {
        Optional<ModuloEnsenanza> docFacPorID= repository.findById(id);
        docFacPorID.ifPresent(user ->{
            repository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito", HttpStatus.ACCEPTED);
    }
}
