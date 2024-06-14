package com.micael.spring.app.entities.moduloUniversidad;

import com.micael.spring.app.entities.moduloGrupo.MateriaGrupo;
import jakarta.persistence.*;
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
@Table(name="aula")
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private int numero;
    @NotNull
    private int capacidad;
    @OneToMany(mappedBy = "aula", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<MateriaGrupo> materiaGrupos;
}
