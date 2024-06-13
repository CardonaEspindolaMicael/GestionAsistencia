package com.micael.spring.app.entities.moduloUniversidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name="facultad")
public class Facultad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String sigla;
    @OneToMany(mappedBy = "facultad", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<DocenteFacultad> docenteFacultades;
    @JsonIgnore
    @OneToMany(mappedBy = "facultad", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ModuloFacultativo> moduloFacultativos;
    @JsonIgnore
    @OneToMany(mappedBy = "facultad", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Carrera> carreras ;
}
