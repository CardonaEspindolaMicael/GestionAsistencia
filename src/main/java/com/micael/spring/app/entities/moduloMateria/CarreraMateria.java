package com.micael.spring.app.entities.moduloMateria;


import com.micael.spring.app.entities.moduloUniversidad.Carrera;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="carrera_materia")
public class CarreraMateria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;
    @ManyToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;
}
