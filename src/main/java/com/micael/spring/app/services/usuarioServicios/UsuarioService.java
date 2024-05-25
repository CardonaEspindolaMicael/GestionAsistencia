package com.micael.spring.app.services.usuarioServicios;

import com.micael.spring.app.entities.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(UUID id);
    Usuario save(Usuario usuario);
    Optional<Usuario> update(UUID id, Usuario usuario);
    Optional<Usuario> delete(UUID id);



}
