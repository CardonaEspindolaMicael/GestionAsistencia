package com.micael.spring.app.entities.administracionDeUsuarios;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="asistencia")
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private boolean asistio=false;
    private LocalDate fecha;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @PrePersist
    public void prePersist() {
        fecha = LocalDate.now();
    }
}
