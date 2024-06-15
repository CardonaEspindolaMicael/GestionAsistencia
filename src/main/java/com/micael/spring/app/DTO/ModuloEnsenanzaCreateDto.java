package com.micael.spring.app.DTO;
import com.micael.spring.app.entities.moduloUniversidad.DocenteEnsena;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ModuloEnsenanzaCreateDto {
    int id;
    ModuloFacultativoDto modulo;
    DocenteEnsena docenteEnsena;
}
