package api.api.persistence;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import api.api.validators.NotificationRequestValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotificationRequestValidator.class)
public @interface ValidRecipient {
    String message() default "Formato do destinatário inválido para o canal selecionado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
