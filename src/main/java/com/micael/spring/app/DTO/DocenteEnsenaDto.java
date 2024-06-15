package com.micael.spring.app.DTO;


import com.micael.spring.app.entities.moduloMateria.Materia;
import com.micael.spring.app.entities.moduloUniversidad.DocenteFacultad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DocenteEnsenaDto {
    int id;
    int gestion;
    DocenteFacultadDto docenteFacultad;
    MateriaDto materia;

    public DocenteEnsenaDto(int id, int gestion, DocenteFacultad docenteFacultad, Materia materia) {
        this.id=id;
        this.gestion=gestion;
        this.docenteFacultad=new DocenteFacultadDto(
                docenteFacultad.getId(),
                docenteFacultad.getUsuario(),
                docenteFacultad.getFacultad()
        );
        this.materia= new MateriaDto(materia.getId(),
                materia.getNombre(), materia.getSiglas(), materia.getNivel(), getMateria().getId_area());
    }
}
