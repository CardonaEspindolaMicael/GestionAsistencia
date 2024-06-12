package com.micael.spring.app.services.administracionDeUsuarios.licenciaServicios;

import com.micael.spring.app.DTO.LicenciaDTO;
import com.micael.spring.app.entities.administracionDeUsuarios.Licencia;
import com.micael.spring.app.entities.administracionDeUsuarios.Usuario;
import com.micael.spring.app.repositories.administracionDeUsuarios.LicenciaRepository;
import com.micael.spring.app.repositories.administracionDeUsuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LicenciaServiceJPA implements LicenciaService{
    @Autowired
    LicenciaRepository repository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<LicenciaDTO> findAll() {
        List<Licencia> licencias= (List<Licencia>) repository.findAll();
        List<LicenciaDTO> licenciaDTOList=new ArrayList<>();
        if(!licencias.isEmpty()){
        for(Licencia licencia:licencias){
            licenciaDTOList.add(new LicenciaDTO(licencia.getId(),licencia.getMotivo(),licencia.getFecha(),licencia.getUsuario().getId()));
        }
        }
        return licenciaDTOList;
    }

    @Transactional
    @Override
    public LicenciaDTO save(LicenciaDTO licenciaDto) {
        Usuario usuario = usuarioRepository.findById(licenciaDto.getId_usuario()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        licenciaDto.prePersist();
        Licencia licencia = new Licencia(licenciaDto.getId(), licenciaDto.getMotivo(), licenciaDto.getFecha(), usuario);
        Licencia response=repository.save(licencia);
        licenciaDto.setId(response.getId());
        return licenciaDto;
    }

    @Transactional
    @Override
    public ResponseEntity<String> update(int id, LicenciaDTO licenciaDto) {
        try {
            Optional<Licencia> licencia=repository.findById(id);
            Usuario usuario = usuarioRepository.findById(licenciaDto.getId_usuario()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            if(licencia.isPresent()){
                Licencia licenciaDB=licencia.orElseThrow();
                licenciaDB.setMotivo(licenciaDto.getMotivo());
                licenciaDB.setFecha(licenciaDto.getFecha());
                licenciaDB.setUsuario(usuario);
                return new ResponseEntity<>("La licencia fue actualizada",HttpStatus.ACCEPTED);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Algo salio mal" , HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>("Vacio" , HttpStatus.NOT_FOUND);
    }


    @Transactional
    @Override
    public ResponseEntity<String>  delete(int id) {
        Optional<Licencia> usuarioPorID= repository.findById(id);
        usuarioPorID.ifPresent(user ->{
            repository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito",HttpStatus.ACCEPTED);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<LicenciaDTO> findById(int id) {
        Optional<Licencia> licencia= repository.findById(id);

        if(licencia.isPresent()){
            Licencia licenciaResponse=licencia.orElseThrow(() -> new RuntimeException("Licencia no encontrada"));
            return Optional.of(new LicenciaDTO(licenciaResponse.getId(), licenciaResponse.getMotivo(), licenciaResponse.getFecha(), licenciaResponse.getUsuario().getId()));

        }
        return Optional.empty();
    }
}
