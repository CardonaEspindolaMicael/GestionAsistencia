package com.micael.spring.app.entities.moduloUniversidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.micael.spring.app.entities.moduloMateria.ModuloEnsenanza;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name="modulo_facultativo")
public class ModuloFacultativo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    @Size(min = 3, max = 255)
    private String nombre;
    @NotNull
    private int numero;
    @ManyToOne
    @JoinColumn(name = "id_facultad")
    private Facultad facultad;
    @JsonIgnore
    @OneToMany(mappedBy = "moduloFacultativo", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ModuloEnsenanza> moduloEnsenanzaList;
}
