package com.micael.spring.app.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CarreraMateriaCreateDto {
    int id;
    int id_carrera;
    int id_materia;
}
