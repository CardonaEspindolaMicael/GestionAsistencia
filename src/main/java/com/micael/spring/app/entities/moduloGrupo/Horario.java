package com.micael.spring.app.entities.moduloGrupo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private LocalTime horaInicio;
    @NotNull
    private LocalTime horaFin;
    @JsonIgnore
    @OneToMany(mappedBy = "horario", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<MateriaGrupo> materiaGrupos;
}
