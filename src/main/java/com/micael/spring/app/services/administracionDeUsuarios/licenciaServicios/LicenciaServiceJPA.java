package com.micael.spring.app.services.administracionDeUsuarios.licenciaServicios;

import com.micael.spring.app.DTO.LicenciaDTO;
import com.micael.spring.app.entities.administracionDeUsuarios.Licencia;
import com.micael.spring.app.entities.administracionDeUsuarios.Usuario;
import com.micael.spring.app.repositories.administracionDeUsuarios.LicenciaRepository;
import com.micael.spring.app.repositories.administracionDeUsuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public Optional<LicenciaDTO> update(int id, Licencia licencia) {
        return Optional.empty();
    }

    @Override
    public Optional<LicenciaDTO> delete(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<LicenciaDTO> findById(int id) {
        return Optional.empty();
    }
}
