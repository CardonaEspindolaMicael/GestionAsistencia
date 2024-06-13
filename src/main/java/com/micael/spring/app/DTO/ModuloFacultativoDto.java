package com.micael.spring.app.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ModuloFacultativoDto {
    int id;
    final String nombre;
    final int numero ;
    final int id_facultad;
}
