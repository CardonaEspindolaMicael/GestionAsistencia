package com.micael.spring.app.services.moduloUniversidad.docenteFacultad;

import com.micael.spring.app.DTO.DocenteFacultadCreateDto;
import com.micael.spring.app.DTO.DocenteFacultadDto;
import com.micael.spring.app.DTO.LicenciaDTO;
import com.micael.spring.app.DTO.UsuarioDto;
import com.micael.spring.app.entities.administracionDeUsuarios.Licencia;
import com.micael.spring.app.entities.administracionDeUsuarios.Usuario;
import com.micael.spring.app.entities.moduloUniversidad.DocenteFacultad;
import com.micael.spring.app.entities.moduloUniversidad.Facultad;
import com.micael.spring.app.repositories.administracionDeUsuarios.UsuarioRepository;
import com.micael.spring.app.repositories.moduloUniversidad.DocenteFacultadRepository;
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
public class DocenteFacultadServiceJPA implements DocenteFacultadService{
    @Autowired
    DocenteFacultadRepository repository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    FacultadRepository facultadRepository;

    @Transactional(readOnly = true)
    @Override
    public List<DocenteFacultadDto> findAll() {
        List<DocenteFacultad> licencias= (List<DocenteFacultad>) repository.findAll();
        List<DocenteFacultadDto> licenciaDTOList=new ArrayList<>();

        if(!licencias.isEmpty()){

            for(DocenteFacultad licencia:licencias){
                Usuario usuario= usuarioRepository.findById(licencia.getUsuario().getId()).orElseThrow();
                Facultad facultad= facultadRepository.findById(licencia.getFacultad().getId()).orElseThrow();
                licenciaDTOList.add(
                        new DocenteFacultadDto(
                                licencia.getId()
                                ,new UsuarioDto(
                                        usuario.getId(),
                                usuario.getNombre(),
                                usuario.getApellidoPaterno(),
                                usuario.getApellidoMaterno(),
                                usuario.getPassword(),
                                usuario.getEmail(),
                                usuario.getTelefono(),
                                usuario.getRol().getId()
                        )
                                ,new Facultad(facultad.getId(),
                                facultad.getNombre(),
                                facultad.getSigla(),null)));
            }
        }
        return licenciaDTOList;
    }
    @Transactional
    @Override
    public DocenteFacultadCreateDto save(DocenteFacultadCreateDto docenteFac) {
        Usuario usuario = usuarioRepository.findById(docenteFac.getId_usuario()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Facultad facultad = facultadRepository.findById(docenteFac.getId_facultad()).orElseThrow(() -> new RuntimeException("Facu no encontrado"));
        DocenteFacultad licencia = new DocenteFacultad(docenteFac.getId(),usuario,facultad);
        DocenteFacultad response=repository.save(licencia);
        docenteFac.setId(response.getId());
        return docenteFac;
    }

    @Transactional
    @Override
    public ResponseEntity<String> update(int id, DocenteFacultadDto docenteFac) {
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<String> delete(int id) {
         Optional<DocenteFacultad> docFacPorID= repository.findById(id);
        docFacPorID.ifPresent(user ->{
            repository.delete(user);
        });
        return new ResponseEntity<>("Eliminado con exito", HttpStatus.ACCEPTED);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<DocenteFacultadDto> findById(int id) {
        Optional<DocenteFacultad> docFacPorID= repository.findById(id);

        if(docFacPorID.isPresent()){
            DocenteFacultad licenciaResponse=docFacPorID.orElseThrow(() -> new RuntimeException("no encontrada"));
            Usuario usuario = usuarioRepository.findById(licenciaResponse.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Facultad facultad= facultadRepository.findById(licenciaResponse.getFacultad().getId()).orElseThrow();

            return Optional.of(new DocenteFacultadDto(licenciaResponse.getId(),new UsuarioDto(
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getApellidoPaterno(),
                    usuario.getApellidoMaterno(),
                    usuario.getPassword(),
                    usuario.getEmail(),
                    usuario.getTelefono(),
                    usuario.getRol().getId()
            ),new Facultad(facultad.getId(),
                    facultad.getNombre(),
                    facultad.getSigla(),null) ));

        }
        return Optional.empty();
    }
}
