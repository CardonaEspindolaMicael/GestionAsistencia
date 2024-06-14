package com.micael.spring.app.repositories.materiaRepository;

import com.micael.spring.app.entities.moduloMateria.Materia;
import org.springframework.data.repository.CrudRepository;

public interface MateriaRepository extends CrudRepository<Materia,Integer> {
}
