package com.micael.spring.app.services.moduloUniversidad.moduloFacultativoServicios;
import com.micael.spring.app.DTO.ModuloFacultativoDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ModuloFacultativoService {

    List<ModuloFacultativoDto> findAll();

    Optional<ModuloFacultativoDto> findById(int id);

    ResponseEntity<String> save(ModuloFacultativoDto modulo);

    ResponseEntity<String> update(int id, ModuloFacultativoDto modulo);

    ResponseEntity<String> delete(int id);
}
