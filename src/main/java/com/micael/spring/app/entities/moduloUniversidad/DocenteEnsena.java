package com.micael.spring.app.entities.moduloUniversidad;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.micael.spring.app.entities.moduloGrupo.MateriaGrupo;
import com.micael.spring.app.entities.moduloMateria.Materia;
import com.micael.spring.app.entities.moduloMateria.ModuloEnsenanza;
import jakarta.persistence.*;
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
@Table(name="docente_ensena")
public class DocenteEnsena {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private int gestion;
    @NotNull
    private int semestre;
    @ManyToOne
    @JoinColumn(name = "id_docenteFacu")
    private DocenteFacultad docenteFacultad;
    @ManyToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;
    @JsonIgnore
    @OneToMany(mappedBy = "docenteEnsena", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<MateriaGrupo> materiaGrupos;
    @JsonIgnore
    @OneToMany(mappedBy = "docenteEnsena", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ModuloEnsenanza> moduloEnsenanzaList;
}
