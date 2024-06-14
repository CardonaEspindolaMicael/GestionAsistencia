package com.micael.spring.app.entities.moduloUniversidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.micael.spring.app.entities.administracionDeUsuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name="docente_facultad")
public class DocenteFacultad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_facultad")
    private Facultad facultad;
    @JsonIgnore
    @OneToMany(mappedBy = "docenteFacultad", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<DocenteEnsena> docenteEnsenaList;

}
