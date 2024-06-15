package com.micael.spring.app.services.administracionDeUsuarios.AsistenciaServicios;

import com.micael.spring.app.DTO.AsistenciaDto;
import com.micael.spring.app.DTO.AsistenciaMarcarDto;
import com.micael.spring.app.DTO.CarreraDto;
import com.micael.spring.app.entities.administracionDeUsuarios.Asistencia;
import com.micael.spring.app.entities.administracionDeUsuarios.Usuario;
import com.micael.spring.app.entities.moduloGrupo.Grupo;
import com.micael.spring.app.entities.moduloGrupo.MateriaGrupo;
import com.micael.spring.app.entities.moduloUniversidad.Carrera;
import com.micael.spring.app.entities.moduloUniversidad.Facultad;
import com.micael.spring.app.repositories.administracionDeUsuarios.AsistenciaRepository;
import com.micael.spring.app.repositories.administracionDeUsuarios.UsuarioRepository;
import com.micael.spring.app.repositories.moduloGrupo.MateriaGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class AsistenciaServiceJPA implements AsistenciaService{
    @Autowired
    AsistenciaRepository repository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    MateriaGrupoRepository materiaGrupoRepository;


    @Transactional(readOnly = true)
    @Override
    public List<AsistenciaDto> findAll() {
        List<Asistencia> asistencias= (List<Asistencia>) repository.findAll();
        List<AsistenciaDto> licenciaDTOList=new ArrayList<>();
     //   crearAsistenciasParaTodosLosUsuario();
        if(!asistencias.isEmpty()){
            for(Asistencia asistencia:asistencias){
                licenciaDTOList.add( new AsistenciaDto( asistencia.getId(),
                        asistencia.isAsistio(),
                        asistencia.getFecha(),
                        asistencia.getHora(),
                        asistencia.getUsuario().getId(),
                        asistencia.getMateriaGrupos().getId()
                ));
            }
        }
        return licenciaDTOList;
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<AsistenciaDto> findById(int id) {
        Optional<Asistencia> licencia= repository.findById(id);

        if(licencia.isPresent()){
            Asistencia licenciaResponse=licencia.orElseThrow(() -> new RuntimeException("no encontrada"));
            return Optional.of(new AsistenciaDto(licenciaResponse.getId(), licenciaResponse.isAsistio(), licenciaResponse.getFecha(), licenciaResponse.getHora()
                    ,licenciaResponse.getUsuario().getId(),licenciaResponse.getMateriaGrupos().getId()));

        }
        return Optional.empty();
    }
    @Transactional
    @Override
    public ResponseEntity<String> save(AsistenciaDto asistencia) {
        Usuario usuario = usuarioRepository.findById(asistencia.getId_usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        MateriaGrupo materiaGrupo = materiaGrupoRepository.findById(asistencia.getId_materiaGrupo()).orElseThrow();
        asistencia.prePersist();
       repository.save(new Asistencia(asistencia.getId(), asistencia.isAsistio(), asistencia.getFecha(), asistencia.getHora()
               ,usuario,materiaGrupo));
        return ResponseEntity.ok("Creado exitosamente");
    }
    @Transactional
    @Override
    public ResponseEntity<String> update(int id, AsistenciaDto asistenciaDto) {
        Asistencia asistencia= repository.findById(id).orElseThrow();
         if(Optional.of(asistencia).isPresent()){
         //  MateriaGrupo materiaGrupo= materiaGrupoRepository.findById()

         }
         return new ResponseEntity<>("No se encuentra", HttpStatus.NOT_FOUND);

    }
    @Transactional
    @Override
    public ResponseEntity<String> delete(int id) {
        Optional<Asistencia> usuarioPorID= repository.findById(id);
        usuarioPorID.ifPresent(user ->{
            repository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito", HttpStatus.ACCEPTED);

    }


    @Transactional
    @Override
    public ResponseEntity<Object> actualizarAsistencia(UUID id_usuario) {
        List<Asistencia> asistenciaList = repository.findAsistenciasByTimeAndUsuarioId(id_usuario, LocalDate.now(), LocalTime.now());
        if (asistenciaList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Collections.singletonMap("message", "No puede marcar asistencias en este horario"));
        }

        for (Asistencia asistencia : asistenciaList) {
            if (asistencia.isAsistio()) {
                return ResponseEntity
                        .status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
                        .body(Collections.singletonMap("message", "Usted ya agrego su asistencia en este horario"));
            }
            Asistencia asistenciaUpdate = repository.findById(asistencia.getId()).orElseThrow();
            asistenciaUpdate.setAsistio(true);
            asistenciaUpdate.setFecha(LocalDate.now());
            asistenciaUpdate.setHora(LocalTime.now());
        }

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(Collections.singletonMap("message", "Asistencia Actualizada"));
    }



}
