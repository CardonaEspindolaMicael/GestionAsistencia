package com.micael.spring.app.repositories.administracionDeUsuarios;

import com.micael.spring.app.entities.administracionDeUsuarios.Asistencia;
import com.micael.spring.app.entities.moduloGrupo.MateriaGrupo;
import com.micael.spring.app.entities.moduloMateria.Materia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface AsistenciaRepository extends CrudRepository<Asistencia, Integer> {

    @Query(value = "SELECT a.* FROM asistencia AS a " +
            "JOIN materia_grupo AS ma ON a.id_materia_grupo = ma.id " +
            "JOIN horario AS h ON h.id = ma.id_horario " +
            "WHERE :time BETWEEN h.hora_inicio AND h.hora_fin " +
            "AND a.id_usuario = :usuarioId " +
            "AND a.fecha = :fecha",
            nativeQuery = true)
    List<Asistencia> findAsistenciasByTimeAndUsuarioId(@Param("usuarioId") UUID usuarioId,
                                                       @Param("fecha") LocalDate fecha,
                                                       @Param("time") LocalTime time);

    @Query(value = "SELECT COUNT(*) " +
            "FROM usuario u " +
            "JOIN docente_facultad df ON u.id = df.id_usuario " +
            "JOIN docente_ensena de ON df.id = de.id_docente_facu " +
            "JOIN materia_grupo mg ON de.id = mg.id_docente_ensena " +
            "WHERE u.id = :userId",
            nativeQuery = true)
    long countMateriasByUsuarioId(@Param("userId") UUID userId);

    @Query(value = "SELECT DISTINCT u.id " +
            "FROM usuario u " +
            "JOIN docente_facultad df ON u.id = df.id_usuario " +
            "JOIN docente_ensena de ON df.id = de.id_docente_facu " +
            "JOIN materia_grupo mg ON de.id = mg.id_docente_ensena",
            nativeQuery = true)
    List<UUID> findUsuarioIds();

    @Query(value = "SELECT mg.id " +
            "FROM usuario u " +
            "JOIN docente_facultad df ON u.id = df.id_usuario " +
            "JOIN docente_ensena de ON df.id = de.id_docente_facu " +
            "JOIN materia_grupo mg ON de.id = mg.id_docente_ensena " +
            "WHERE u.id = :userId",
            nativeQuery = true)
    List<Integer> countDetalleByUsuarioId(@Param("userId") UUID userId);

    @Query("SELECT a FROM Asistencia a WHERE a.usuario.id = :usuarioId")
    List<Asistencia> findByUsuario_Id(UUID usuarioId);


    @Query("SELECT a FROM Asistencia a WHERE a.usuario.id = :usuarioId AND a.fecha=:fecha")
    List<Asistencia> findByDateAndUsuario_Id(@Param("usuarioId") UUID usuarioId,
                                             @Param("fecha") LocalDate fecha);

}
