package com.micael.spring.app.repositories;


import com.micael.spring.app.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UsuarioRepository extends CrudRepository<Usuario, UUID> {


}
