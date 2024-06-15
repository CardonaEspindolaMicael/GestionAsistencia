package com.micael.spring.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DocenteEnsenaCreateDto {
    int id;
    int gestion;
    int id_docenteFacultad;
    int id_materia;
}
