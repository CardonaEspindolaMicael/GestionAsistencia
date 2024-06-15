package com.micael.spring.app.services.moduloMateria.moduloEnsenanzaServicios;

import com.micael.spring.app.DTO.ModuloEnsenanzaCreateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ModuloEnsenanzaService {
    List<ModuloEnsenanzaCreateDto> findAll();

    Optional<ModuloEnsenanzaCreateDto> findById(int id);

    ResponseEntity<String> save(ModuloEnsenanzaCreateDto moduloEnsenanza);

    ResponseEntity<String> update(int id, ModuloEnsenanzaCreateDto  moduloEnsenanza);

    ResponseEntity<String> delete(int id);
}
