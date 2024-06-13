package com.micael.spring.app.services.moduloUniversidad.carreraServicios;
import com.micael.spring.app.DTO.CarreraDto;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface CarreraService {

    List<CarreraDto> findAll();

    Optional<CarreraDto> findById(int id);

    ResponseEntity<String> save(CarreraDto carrera);

    ResponseEntity<String> update(int id, CarreraDto carrera);

    ResponseEntity<String> delete(int id);


}
