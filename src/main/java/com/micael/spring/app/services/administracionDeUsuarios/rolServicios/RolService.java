package com.micael.spring.app.services.administracionDeUsuarios.rolServicios;

import com.micael.spring.app.entities.administracionDeUsuarios.Rol;

import java.util.Optional;
import java.util.UUID;

public interface RolService {
    Optional<Rol> findById(UUID id);
}
