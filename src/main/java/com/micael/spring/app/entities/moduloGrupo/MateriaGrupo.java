package com.micael.spring.app.entities.moduloGrupo;


import com.micael.spring.app.entities.administracionDeUsuarios.Asistencia;
import com.micael.spring.app.entities.moduloMateria.Materia;
import com.micael.spring.app.entities.moduloUniversidad.Aula;
import com.micael.spring.app.entities.moduloUniversidad.DocenteEnsena;
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
@Table(name="materia_grupo")
public class MateriaGrupo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_docenteEnsena")
    private DocenteEnsena docenteEnsena;
    @ManyToOne
    @JoinColumn(name = "id_aula")
    private Aula aula;
    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;
    @ManyToOne
    @JoinColumn(name = "id_horario")
    private Horario horario;
    @OneToMany(mappedBy = "materiaGrupos", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Asistencia> asistencias;
}
