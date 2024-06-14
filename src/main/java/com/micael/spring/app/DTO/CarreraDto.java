package com.micael.spring.app.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CarreraDto {
    int id;
    final String nombre;
    final int anios;
    final int id_facultad;
}
