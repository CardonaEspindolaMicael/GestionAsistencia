package com.micael.spring.app.repositories.materiaRepository;

import com.micael.spring.app.entities.moduloMateria.Materia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MateriaRepository extends CrudRepository<Materia,Integer> {
    @Query("Select m From Materia m WHERE m.area.id=?1")
    List<Materia> findMateriasByAreaId(int areaId);
}
