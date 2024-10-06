package com.dme.DormitoryProject.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(value = {ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MailUniqueCheckValidator.class})
public @interface MailUniqueCheck {
    String message() default "bu mail başka birine ait";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
