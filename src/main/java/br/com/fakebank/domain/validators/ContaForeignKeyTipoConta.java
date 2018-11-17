package br.com.fakebank.domain.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ContaForeignKeyTipoConta {
    
    public String message() default "Tipo de conta não encontrado!";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};

}
