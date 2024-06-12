package com.micael.spring.app.DTO;

import jakarta.persistence.PrePersist;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class LicenciaDTO implements Serializable {
     int id;
    final String motivo;
    LocalDate fecha;
    final UUID id_usuario;

    @PrePersist
    public void prePersist() {
        fecha = LocalDate.now();
    }
}

