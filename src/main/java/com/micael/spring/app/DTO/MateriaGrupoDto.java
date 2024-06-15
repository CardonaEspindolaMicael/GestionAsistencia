package com.micael.spring.app.DTO;

import com.micael.spring.app.entities.moduloGrupo.Grupo;
import com.micael.spring.app.entities.moduloGrupo.Horario;
import com.micael.spring.app.entities.moduloUniversidad.Aula;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MateriaGrupoDto {
    int id;
    DocenteEnsenaDto docenteEnsena;
    Aula aula;
    Grupo id_grupo;
    Horario id_horario;
}
