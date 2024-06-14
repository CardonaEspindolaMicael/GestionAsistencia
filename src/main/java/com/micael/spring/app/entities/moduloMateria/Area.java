package com.micael.spring.app.entities.moduloMateria;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@Table(name="area")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String nombre;
    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Materia> materias;
}
