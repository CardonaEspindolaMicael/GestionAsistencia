package com.micael.spring.app.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ModuloEnsenazaDto {
    int id;
    int id_modulo;
    int id_docenteEnsena;
}
