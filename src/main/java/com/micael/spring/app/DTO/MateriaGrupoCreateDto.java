package com.micael.spring.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MateriaGrupoCreateDto {
    int id;
    int id_docenteEnsena;
    int id_aula;
    int id_grupo;
    int id_horario;
}
