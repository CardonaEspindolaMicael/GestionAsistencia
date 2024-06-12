package com.micael.spring.app.entities.administracionDeUsuarios;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="licencia")
public class Licencia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    @NotBlank
    @Size(min=3, max = 1000)
    private String motivo;
    private LocalDate fecha;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;




}

