package com.micael.spring.app.validation.telefono;

import com.micael.spring.app.services.administracionDeUsuarios.usuarioServicios.UsuarioService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistByTelefonoValidation implements ConstraintValidator<ExistByTelefono,Long> {
    @Autowired
    private UsuarioService service;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(service==null) return true;
        return !service.existsByTelefono(value);
    }
}
