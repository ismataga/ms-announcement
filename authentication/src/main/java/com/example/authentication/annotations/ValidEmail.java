package com.example.authentication.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.authentication.model.constants.Regex.EMAIL_REGEX;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = EMAIL_REGEX, message = "invalid email")
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface ValidEmail {

  String message() default "invalid email";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
