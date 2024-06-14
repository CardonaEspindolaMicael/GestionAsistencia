package com.micael.spring.app.DTO;
import com.micael.spring.app.entities.moduloMateria.Area;
import com.micael.spring.app.entities.moduloMateria.CarreraMateria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CarreraMateriaDto {
    int id;
    CarreraDto carrera;
    MateriaDto materia;
}
