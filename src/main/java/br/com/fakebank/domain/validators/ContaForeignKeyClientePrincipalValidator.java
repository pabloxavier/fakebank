package br.com.fakebank.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fakebank.service.ContaService;

public class ContaForeignKeyClientePrincipalValidator implements ConstraintValidator<ContaForeignKeyClientePrincipal, Integer>{
    
    @Autowired
    private ContaService service; 

	@Override
	public boolean isValid(Integer cliente, ConstraintValidatorContext context) {
		return service.isClientePrincipalPresent(cliente);
	}
}
