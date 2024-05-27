package com.micael.spring.app.validation.email;

import com.micael.spring.app.services.usuarioServicios.UsuarioService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsByEmailValidation implements ConstraintValidator<ExistsByEmail,String> {
    @Autowired
    private UsuarioService service;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
       if(service==null) return true;
        return !service.existsByEmail(value);
    }
}
