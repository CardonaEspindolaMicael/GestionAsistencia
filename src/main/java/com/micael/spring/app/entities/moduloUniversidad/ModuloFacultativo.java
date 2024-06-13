package com.micael.spring.app.entities.moduloUniversidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @NotBlank
    private int numero;
    @ManyToOne
    @JoinColumn(name = "id_facultad")
    private Facultad facultad;
}
