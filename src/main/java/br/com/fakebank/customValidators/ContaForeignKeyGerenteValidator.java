package br.com.fakebank.customValidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fakebank.service.ContaService;

public class ContaForeignKeyGerenteValidator implements ConstraintValidator<ContaForeignKeyGerente, Integer>{
	@Autowired
	private ContaService service; 

	@Override
	public boolean isValid(Integer codigoGerente, ConstraintValidatorContext context) {
	//	return !service.cnpjJaExiste(cnpj);
		return true;
	}

}
