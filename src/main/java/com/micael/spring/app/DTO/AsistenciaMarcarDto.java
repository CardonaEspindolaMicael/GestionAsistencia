package com.micael.spring.app.DTO;


import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
public class AsistenciaMarcarDto {
    boolean asistio;
    LocalDate fecha;
    LocalTime hora;

    @PrePersist
    public void prePersist() {
        fecha = LocalDate.now();
        hora= LocalTime.now();
    }
}
