package com.micael.spring.app.validation.email;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = ExistsByEmailValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByEmail {
    String message() default "ya existe, escoja uno nuevo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
