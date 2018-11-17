package br.com.fakebank.domain.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CnpjValidValidator.class)
public @interface CnpjValid {

    String message() default "CNPJ não é valido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
