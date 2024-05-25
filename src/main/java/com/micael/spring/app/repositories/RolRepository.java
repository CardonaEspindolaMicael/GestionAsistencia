package com.micael.spring.app.repositories;

import com.micael.spring.app.entities.Rol;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RolRepository extends CrudRepository<Rol, UUID> {
}
