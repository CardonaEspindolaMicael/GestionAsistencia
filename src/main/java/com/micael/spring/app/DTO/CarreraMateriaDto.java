package com.micael.spring.app.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class CarreraMateriaDto {
    int id;
    CarreraDto carrera;
    MateriaDto materia;
}
