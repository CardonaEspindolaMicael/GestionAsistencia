package com.micael.spring.app.repositories.administracionDeUsuarios;

import com.micael.spring.app.entities.administracionDeUsuarios.Rol;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RolRepository  extends CrudRepository<Rol, UUID> {

}
