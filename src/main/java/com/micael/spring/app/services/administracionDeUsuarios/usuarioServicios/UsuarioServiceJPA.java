package com.micael.spring.app.services.administracionDeUsuarios.usuarioServicios;

import com.micael.spring.app.DTO.UsuarioDto;
import com.micael.spring.app.entities.administracionDeUsuarios.Rol;
import com.micael.spring.app.entities.administracionDeUsuarios.Usuario;
import com.micael.spring.app.repositories.administracionDeUsuarios.RolRepository;
import com.micael.spring.app.repositories.administracionDeUsuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceJPA implements UsuarioService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private RolRepository rolRepository;


    @Transactional(readOnly = true)
    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public UsuarioDto save(UsuarioDto usuarioDto) {
        String passwordEncoded= passwordEncoder.encode(usuarioDto.getPassword());
        Rol rol=rolRepository.findById(usuarioDto.getId_rol()).orElseThrow(()-> new RuntimeException("El rol no existe"));
        Usuario usuario=new Usuario(usuarioDto.getId(), usuarioDto.getNombre(), usuarioDto.getApellidoPaterno(),
                usuarioDto.getApellidoMaterno(), passwordEncoded, usuarioDto.getEmail(),usuarioDto.getTelefono(),rol,null,null );
        Usuario response=repository.save(usuario);
        usuarioDto.setId(response.getId());
        return usuarioDto;
    }
    @Transactional
    @Override
    public Optional<Usuario> update(UUID id, Usuario usuario) {
        Optional<Usuario> usuarioPorID= repository.findById(id);
        if (usuarioPorID.isPresent()) {
            Usuario usuarioDB= usuarioPorID.orElseThrow();
            usuarioDB.setNombre(usuario.getNombre());
            usuarioDB.setApellidoMaterno(usuario.getApellidoMaterno());
            usuarioDB.setApellidoPaterno(usuario.getApellidoPaterno());
            usuarioDB.setEmail(usuario.getEmail());
            usuarioDB.setTelefono(usuario.getTelefono());
            usuarioDB.setRol(usuario.getRol());
            String passwordEncoded= passwordEncoder.encode(usuario.getPassword());
            usuarioDB.setPassword(passwordEncoded);
        }

        return usuarioPorID;
    }
    @Transactional
    @Override
    public Optional<Usuario> delete(UUID id) {
        Optional<Usuario> usuarioPorID= repository.findById(id);
        usuarioPorID.ifPresent(user ->{
            repository.delete(user);
        });
        return usuarioPorID;
    }

    @Override
    public boolean existsByTelefono(Long telefono) {
        return repository.existsByTelefono(telefono);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email);
    }


}
