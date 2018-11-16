package br.com.fakebank.domain.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DominioCodigoValidator.class)
public @interface DominioCodigoValid {

    String message() default "Código do Domínio não é válido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
