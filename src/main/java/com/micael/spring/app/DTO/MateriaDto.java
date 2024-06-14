package com.micael.spring.app.DTO;

import com.micael.spring.app.entities.moduloMateria.Area;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MateriaDto {
    private int id;
    private String nombre;
    private String siglas;
    private int nivel;
    private int id_area;
}
