package com.micael.spring.app.entities.moduloMateria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.micael.spring.app.entities.moduloUniversidad.DocenteEnsena;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name="materia")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String siglas;
    @NotNull
    private int nivel;
    @ManyToOne
    @JoinColumn(name = "id_area")
    private Area area;
    @JsonIgnore
    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<DocenteEnsena> docenteEnsenaList;
}
