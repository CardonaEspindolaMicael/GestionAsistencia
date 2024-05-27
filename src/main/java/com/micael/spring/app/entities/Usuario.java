package com.micael.spring.app.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micael.spring.app.validation.email.ExistsByEmail;
import com.micael.spring.app.validation.telefono.ExistByTelefono;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 3, max = 255)
    private String nombre;
    @Column(name ="apellido_paterno" , nullable = false)
    private String apellidoPaterno;
    @NotBlank
    @Size(min = 3, max = 255)
    @Column(name ="apellido_materno" , nullable = false)
    private String apellidoMaterno;
    @Size(min = 4, max=255)
    @NotEmpty
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(nullable = false)
    @NotBlank
    @Email
    @ExistsByEmail
    private String email;
    @Column(nullable = false)
    @ExistByTelefono
    private Long telefono;
    @ManyToOne()
    @JoinColumn(name = "id_rol")
    private Rol rol;

}
