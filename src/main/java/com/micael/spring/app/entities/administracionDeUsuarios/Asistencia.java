package com.micael.spring.app.entities.administracionDeUsuarios;


import com.micael.spring.app.entities.moduloGrupo.MateriaGrupo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private boolean asistio;
    private LocalDate fecha;
    private LocalTime hora;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_materiaGrupo")
    private MateriaGrupo materiaGrupos;

}
