package com.micael.spring.app.entities.administracionDeUsuarios;


import com.micael.spring.app.entities.moduloGrupo.MateriaGrupo;
import jakarta.persistence.*;
import lombok.*;

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
    @ManyToOne
    @JoinColumn(name = "id_materiaGrupo")
    private MateriaGrupo materiaGrupos;
    @PrePersist
    public void prePersist() {
        fecha = LocalDate.now();
    }
}
