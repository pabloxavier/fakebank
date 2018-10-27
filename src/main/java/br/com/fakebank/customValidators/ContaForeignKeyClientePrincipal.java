package br.com.fakebank.customValidators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ContaForeignKeyClientePrincipal {
    
    public String message() default "Código do cliente não encontrado!";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};

}
