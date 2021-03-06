package br.com.fakebank.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fakebank.service.ContaService;

public class ContaForeignKeyTipoContaValidator implements ConstraintValidator<ContaForeignKeyTipoConta, Integer> {
    @Autowired
    private ContaService service; 

	@Override
	public boolean isValid(Integer tipoConta, ConstraintValidatorContext context) {
		return service.isTipoContaPresent(tipoConta);
	}

}
