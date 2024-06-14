package com.micael.spring.app.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class AreaDto {
    int id;
    final String nombre;
    List<MateriaDto> materia;
}
