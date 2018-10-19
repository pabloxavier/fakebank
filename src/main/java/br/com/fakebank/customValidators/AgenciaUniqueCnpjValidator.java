package br.com.fakebank.customValidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fakebank.service.AgenciaService;

public class AgenciaUniqueCnpjValidator implements ConstraintValidator<AgenciaUniqueCnpj, String>{

	@Autowired
	private AgenciaService service;
	
	@Override
	public boolean isValid(String cnpj, ConstraintValidatorContext context) {
		return !service.cnpjJaExiste(cnpj);
	}


	
}
