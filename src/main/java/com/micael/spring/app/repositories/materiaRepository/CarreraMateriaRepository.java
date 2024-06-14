package com.micael.spring.app.repositories.materiaRepository;
import com.micael.spring.app.entities.moduloMateria.CarreraMateria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarreraMateriaRepository extends CrudRepository<CarreraMateria,Integer> {
    @Query("SELECT cm FROM CarreraMateria cm WHERE cm.carrera.id = :carreraId")
    List<CarreraMateria> findByCarreraId(@Param("carreraId") Integer carreraId);
}



