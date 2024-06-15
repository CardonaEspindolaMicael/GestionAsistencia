package com.micael.spring.app.services.moduloGrupo.materiaGrupoServicios;

import com.micael.spring.app.DTO.*;
import com.micael.spring.app.entities.moduloGrupo.Grupo;
import com.micael.spring.app.entities.moduloGrupo.Horario;
import com.micael.spring.app.entities.moduloGrupo.MateriaGrupo;
import com.micael.spring.app.entities.moduloUniversidad.Aula;
import com.micael.spring.app.entities.moduloUniversidad.DocenteEnsena;
import com.micael.spring.app.repositories.moduloGrupo.GrupoRepository;
import com.micael.spring.app.repositories.moduloGrupo.HorarioRepository;
import com.micael.spring.app.repositories.moduloGrupo.MateriaGrupoRepository;
import com.micael.spring.app.repositories.moduloUniversidad.AulaRepository;
import com.micael.spring.app.repositories.moduloUniversidad.DocenteEnsenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaGrupoServiceJPA  implements MateriaGrupoService{
    @Autowired
    MateriaGrupoRepository repository;
    @Autowired
    DocenteEnsenaRepository docenteEnsenaRepository;
    @Autowired
    AulaRepository aulaRepository;
    @Autowired
    GrupoRepository grupoRepository;
    @Autowired
    HorarioRepository horarioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<MateriaGrupoDto> findAll() {
        List<MateriaGrupo> materiaGrupos= (List<MateriaGrupo>) repository.findAll();
        List<MateriaGrupoDto> licenciaDTOList=new ArrayList<>();

        if(!materiaGrupos.isEmpty()){

            for(MateriaGrupo materiaGrupo:materiaGrupos){
                DocenteEnsena docenteEnsena= docenteEnsenaRepository.findById(materiaGrupo.getDocenteEnsena().getId()).orElseThrow();
                if(Optional.of(docenteEnsena).isPresent()){


                licenciaDTOList.add(
                        new MateriaGrupoDto(materiaGrupo.getId(),
                                new DocenteEnsenaDto(docenteEnsena.getId(),docenteEnsena.getGestion(),docenteEnsena.getDocenteFacultad(),docenteEnsena.getMateria()),
                                materiaGrupo.getAula(),materiaGrupo.getGrupo(),materiaGrupo.getHorario()

                ));
                }
            }
        }
        return licenciaDTOList;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<MateriaGrupoDto> findById(int id) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public ResponseEntity<String> save(MateriaGrupoCreateDto materiaGrupoCreateDto) {
        DocenteEnsena docenteEnsena=docenteEnsenaRepository.findById(materiaGrupoCreateDto.getId_docenteEnsena()).orElseThrow();
        Aula aula=aulaRepository.findById(materiaGrupoCreateDto.getId_aula()).orElseThrow();
        Grupo grupo=grupoRepository.findById(materiaGrupoCreateDto.getId_grupo()).orElseThrow();
        Horario horario=horarioRepository.findById(materiaGrupoCreateDto.getId_horario()).orElseThrow();

        repository.save(new MateriaGrupo(materiaGrupoCreateDto.getId(), docenteEnsena,aula,grupo,horario,null));
        return  ResponseEntity.ok("Todo posi");
    }

    @Transactional
    @Override
    public ResponseEntity<String> update(int id, MateriaGrupoCreateDto materiaGrupoCreateDto) {
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<String> delete(int id) {
        Optional<MateriaGrupo> usuarioPorID= repository.findById(id);
        usuarioPorID.ifPresent(user ->{
            repository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito", HttpStatus.ACCEPTED);
    }

}
