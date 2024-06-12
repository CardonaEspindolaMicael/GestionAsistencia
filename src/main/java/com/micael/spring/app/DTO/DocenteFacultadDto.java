package com.micael.spring.app.DTO;

import com.micael.spring.app.entities.moduloUniversidad.Facultad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@AllArgsConstructor
@Getter
@Setter
public class DocenteFacultadDto implements Serializable {
    public int id;
    final UsuarioDto usuario;
    final Facultad facultad;
}
