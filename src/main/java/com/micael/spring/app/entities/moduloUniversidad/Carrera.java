package com.micael.spring.app.entities.moduloUniversidad;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.micael.spring.app.entities.moduloMateria.CarreraMateria;
import com.micael.spring.app.entities.moduloMateria.Materia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="carrera")
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    @Size(min = 3, max = 255)
    private String nombre;
    @NotNull
    private int anios;
    @ManyToOne
    @JoinColumn(name = "id_facultad")
    private Facultad facultad;
    @JsonIgnore
    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CarreraMateria> carreraMaterias;

}
