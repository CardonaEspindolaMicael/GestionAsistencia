package com.micael.spring.app.services.administracionDeUsuarios.licenciaServicios;

import com.micael.spring.app.DTO.LicenciaDTO;
import com.micael.spring.app.entities.administracionDeUsuarios.Licencia;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface LicenciaService {
    List<LicenciaDTO> findAll();
    LicenciaDTO save(LicenciaDTO licencia);
    Optional<LicenciaDTO> update(int id, Licencia licencia);
    Optional<LicenciaDTO> delete(int id);
    Optional<LicenciaDTO> findById(int id);

}
