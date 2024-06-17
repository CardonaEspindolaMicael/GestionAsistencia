package com.micael.spring.app.services.moduloUniversidad.carreraServicios;

import com.micael.spring.app.DTO.CarreraDto;
import com.micael.spring.app.DTO.LicenciaDTO;
import com.micael.spring.app.entities.administracionDeUsuarios.Licencia;
import com.micael.spring.app.entities.administracionDeUsuarios.Usuario;
import com.micael.spring.app.entities.moduloUniversidad.Carrera;
import com.micael.spring.app.entities.moduloUniversidad.Facultad;
import com.micael.spring.app.repositories.moduloUniversidad.CarreraRepository;
import com.micael.spring.app.repositories.moduloUniversidad.FacultadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarreraServiceJPA implements CarreraService{
    @Autowired
    CarreraRepository repository;
    @Autowired
    FacultadRepository facultadRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CarreraDto> findAll() {
        List<Carrera> licencias= (List<Carrera>) repository.findAll();
        List<CarreraDto> licenciaDTOList=new ArrayList<>();
        if(!licencias.isEmpty()){
            for(Carrera licencia:licencias){
                licenciaDTOList.add(
                        new CarreraDto(
                                licencia.getId()
                                ,licencia.getNombre(),
                                licencia.getAnios(),
                                licencia.getFacultad().getId()
                               ));
            }
        }
        return licenciaDTOList;
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<CarreraDto> findById(int id) {
        Optional<Carrera> licencia= repository.findById(id);

        if(licencia.isPresent()){
            Carrera licenciaResponse=licencia.orElseThrow(() -> new RuntimeException("Licencia no encontrada"));
            return Optional.of(new CarreraDto(licenciaResponse.getId(), licenciaResponse.getNombre(), licenciaResponse.getAnios(), licenciaResponse.getFacultad().getId()));

        }
        return Optional.empty();
    }
    @Transactional
    @Override
    public ResponseEntity<String> save(CarreraDto carreraDto) {
        Facultad facultad = facultadRepository.findById(carreraDto.getId_facultad())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        System.out.println(carreraDto.getAnios()+' '+carreraDto.getNombre());
        Carrera licencia = new Carrera(carreraDto.getId(),
                carreraDto.getNombre(), carreraDto.getAnios(),facultad,null);
        Carrera response=repository.save(licencia);
        return ResponseEntity.ok("Creado exitosamente");
    }

    @Transactional
    @Override
    public ResponseEntity<String> update(int id, CarreraDto carrera) {
        return ResponseEntity.ok("Proximamente");
    }

    @Transactional
    @Override
    public ResponseEntity<String> delete(int id) {
        Optional<Carrera> usuarioPorID= repository.findById(id);
        usuarioPorID.ifPresent(user ->{
            repository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito", HttpStatus.ACCEPTED);
    }
}
