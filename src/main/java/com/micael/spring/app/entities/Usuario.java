package com.micael.spring.app.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nombre;
    @Column(name ="apellido_paterno" , nullable = false)
    private String apellidoPaterno;
    @Column(name ="apellido_materno" , nullable = false)
    private String apellidoMaterno;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Long telefono;
    @ManyToOne()
    @JoinColumn(name = "id_rol")
    private Rol rol;

}
