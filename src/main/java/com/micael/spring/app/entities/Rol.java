package com.micael.spring.app.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rol")

public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    @Column(nullable = false)
    private String cargo;
    @Column(nullable = false, length = 525)
    private String descripcion;
    @OneToMany(mappedBy = "rol",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Usuario> usuarios;
}
