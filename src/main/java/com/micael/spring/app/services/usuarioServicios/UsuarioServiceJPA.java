package com.micael.spring.app.services.usuarioServicios;

import com.micael.spring.app.entities.Usuario;
import com.micael.spring.app.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceJPA implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;

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
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
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
}
