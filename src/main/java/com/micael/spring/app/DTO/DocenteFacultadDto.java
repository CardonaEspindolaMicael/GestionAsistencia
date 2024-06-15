package com.micael.spring.app.DTO;

import com.micael.spring.app.entities.administracionDeUsuarios.Usuario;
import com.micael.spring.app.entities.moduloUniversidad.Facultad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@AllArgsConstructor
@Getter
@Setter
public class DocenteFacultadDto implements Serializable {
    public int id;
     UsuarioDto usuario;
     Facultad facultad;

    public DocenteFacultadDto(int id, Usuario usuario, Facultad facultad) {
        this.id=id;
     this.usuario= new UsuarioDto(
             usuario.getId(),
             usuario.getNombre(),
             usuario.getApellidoPaterno(),
             usuario.getApellidoMaterno(),
             usuario.getPassword(),
             usuario.getEmail(),
             usuario.getTelefono(),
             usuario.getRol().getId());
     this.facultad=facultad;
    }
}
