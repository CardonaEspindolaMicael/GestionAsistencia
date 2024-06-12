package com.micael.spring.app.DTO;

import com.micael.spring.app.entities.moduloUniversidad.Facultad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class DocenteFacultadCreateDto {
    public int id;
    final UUID id_usuario;
    final int id_facultad;
}
