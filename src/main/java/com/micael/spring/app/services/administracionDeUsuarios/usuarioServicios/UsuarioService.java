package com.micael.spring.app.services.administracionDeUsuarios.usuarioServicios;

import com.micael.spring.app.DTO.UsuarioDto;
import com.micael.spring.app.entities.administracionDeUsuarios.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(UUID id);
    UsuarioDto save(UsuarioDto usuario);
    Optional<Usuario> update(UUID id, Usuario usuario);
    Optional<Usuario> delete(UUID id);
    boolean existsByTelefono(Long telefono);
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);
}
