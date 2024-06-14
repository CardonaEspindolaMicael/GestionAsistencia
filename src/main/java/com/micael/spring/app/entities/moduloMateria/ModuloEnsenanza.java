package com.micael.spring.app.entities.moduloMateria;


import com.micael.spring.app.entities.moduloUniversidad.DocenteEnsena;
import com.micael.spring.app.entities.moduloUniversidad.ModuloFacultativo;
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
@Table(name="modulo_ensenanza")
public class ModuloEnsenanza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_modulo")
    private ModuloFacultativo moduloFacultativo;
    @ManyToOne
    @JoinColumn(name = "id_docenteEnsena")
    private DocenteEnsena docenteEnsena;
}
