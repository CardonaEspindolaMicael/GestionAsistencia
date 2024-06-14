package com.micael.spring.app.entities.moduloGrupo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.micael.spring.app.entities.moduloUniversidad.DocenteEnsena;
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
@Table(name="grupo")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String sigla;
    @JsonIgnore
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<MateriaGrupo> materiaGrupos;

}
