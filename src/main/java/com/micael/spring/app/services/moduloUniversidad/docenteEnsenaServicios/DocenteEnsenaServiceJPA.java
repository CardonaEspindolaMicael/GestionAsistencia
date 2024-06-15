package com.micael.spring.app.services.moduloUniversidad.docenteEnsenaServicios;

import com.micael.spring.app.DTO.*;
import com.micael.spring.app.entities.administracionDeUsuarios.Usuario;
import com.micael.spring.app.entities.moduloMateria.Materia;
import com.micael.spring.app.entities.moduloUniversidad.DocenteEnsena;
import com.micael.spring.app.entities.moduloUniversidad.DocenteFacultad;
import com.micael.spring.app.entities.moduloUniversidad.Facultad;
import com.micael.spring.app.repositories.materiaRepository.MateriaRepository;
import com.micael.spring.app.repositories.moduloUniversidad.DocenteEnsenaRepository;
import com.micael.spring.app.repositories.moduloUniversidad.DocenteFacultadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class DocenteEnsenaServiceJPA implements DocenteEnsenaService{
    @Autowired
    DocenteEnsenaRepository repository;
    @Autowired
    DocenteFacultadRepository docenteFacultadRepository;
    @Autowired
    MateriaRepository materiaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<DocenteEnsenaDto> findAll() {
        List<DocenteEnsena> docenteEnsenaList= (List<DocenteEnsena>) repository.findAll();
        List<DocenteEnsenaDto> licenciaDTOList=new ArrayList<>();

        if(!docenteEnsenaList.isEmpty()){

            for(DocenteEnsena docenteEnsena:docenteEnsenaList){
                DocenteFacultad docenteFacultad= docenteFacultadRepository.findById(docenteEnsena.getDocenteFacultad().getId()).orElseThrow();
                Materia materia= materiaRepository.findById(docenteEnsena.getMateria().getId()).orElseThrow();
                licenciaDTOList.add(
                        new DocenteEnsenaDto(
                                docenteEnsena.getId(),
                                docenteEnsena.getGestion(),
                                new DocenteFacultadDto(
                                        docenteFacultad.getId(),
                                        docenteFacultad.getUsuario(),
                                        docenteFacultad.getFacultad()
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
    public Optional<DocenteEnsenaDto> findById(int id) {
        Optional<DocenteEnsena> docFacPorID = repository.findById(id);

        if (docFacPorID.isPresent()) {
            DocenteEnsena licenciaResponse = docFacPorID.orElseThrow(() -> new RuntimeException("no encontrada"));
            DocenteFacultad docenteFacultad = docenteFacultadRepository.findById(licenciaResponse.getDocenteFacultad().getId()).orElseThrow();
            Materia materia = materiaRepository.findById(licenciaResponse.getMateria().getId()).orElseThrow();
            return Optional.of(
                    new DocenteEnsenaDto(licenciaResponse.getId(),licenciaResponse.getGestion(),docenteFacultad,materia)
            );
        }
        return Optional.empty();
    }
    @Override
    public ResponseEntity<String> save(DocenteEnsenaCreateDto docenteEnsena) {
        DocenteFacultad docenteFacultad= docenteFacultadRepository.findById(docenteEnsena.getId_docenteFacultad()).orElseThrow();
        Materia materia= materiaRepository.findById(docenteEnsena.getId_materia()).orElseThrow();
        DocenteEnsena licencia = new DocenteEnsena(docenteEnsena.getId(),docenteEnsena.getGestion(),1,docenteFacultad,materia,null,null);
         repository.save(licencia);
        return ResponseEntity.ok("creado correctamente");
    }

    @Override
    public ResponseEntity<String> update(int id, DocenteEnsenaCreateDto docenteEnsena) {
return null;
    }

    @Override
    public ResponseEntity<String> delete(int id) {
        Optional<DocenteEnsena> docFacPorID= repository.findById(id);
        docFacPorID.ifPresent(user ->{
            repository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito", HttpStatus.ACCEPTED);
    }
}
