package com.micael.spring.app.DTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.micael.spring.app.entities.administracionDeUsuarios.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDto implements Serializable {

    public UUID id;

    final String nombre;

    final  String apellidoPaterno;

    final String apellidoMaterno;

    final String password;

    final String email;

    final Long telefono;

    public UUID id_rol;

}
