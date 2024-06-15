package com.micael.spring.app.DTO;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class AsistenciaDto {
    int id;
    boolean asistio=false;
    LocalDate fecha;
    LocalTime hora;
    UUID id_usuario;
    int id_materiaGrupo;

    @PrePersist
    public void prePersist() {
        fecha = LocalDate.now();
        hora= LocalTime.now();
        asistio=false;
    }
}
