package br.com.fakebank.customValidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fakebank.service.ContaService;

public class ContaForeignKeySituacaoContaValidator  implements ConstraintValidator<ContaForeignKeySituacaoConta, Integer>{
	@Autowired
	private ContaService service; 

	@Override
	public boolean isValid(Integer codigoSituacaoConta, ConstraintValidatorContext context) {
		return service.isSituacaoContaPresent(codigoSituacaoConta);
	}

}
