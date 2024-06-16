package com.micael.spring.app.repositories.moduloGrupo;

import com.micael.spring.app.entities.moduloGrupo.MateriaGrupo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MateriaGrupoRepository extends CrudRepository<MateriaGrupo,Integer> {

    @Query(value = "SELECT mg.* " +
            "FROM usuario u " +
            "JOIN docente_facultad df ON u.id = df.id_usuario " +
            "JOIN docente_ensena de ON df.id = de.id_docente_facu " +
            "JOIN materia_grupo mg ON de.id = mg.id_docente_ensena " +
            "WHERE u.id = :userId",
            nativeQuery = true)
    List<MateriaGrupo> findMateriaGrupoById(@Param("userId") UUID userId);
}
