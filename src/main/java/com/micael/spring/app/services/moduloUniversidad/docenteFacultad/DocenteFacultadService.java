package com.micael.spring.app.services.moduloUniversidad.docenteFacultad;
import com.micael.spring.app.DTO.DocenteFacultadDto;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface DocenteFacultadService {
    List<DocenteFacultadDto> findAll();

    DocenteFacultadDto save(DocenteFacultadDto docenteFac);

    ResponseEntity<String> update(int id, DocenteFacultadDto docenteFac);

    ResponseEntity<String> delete(int id);

    Optional<DocenteFacultadDto> findById(int id);

}
