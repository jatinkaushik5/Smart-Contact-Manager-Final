package com.SmartContactManager.Smart.Contact.Manager.Final.CustomAnnotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileValidator.class)
public @interface FileValid {
    String message() default "invalid file";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
