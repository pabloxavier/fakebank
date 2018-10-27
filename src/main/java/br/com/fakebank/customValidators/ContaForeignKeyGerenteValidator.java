package br.com.fakebank.customValidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fakebank.service.ContaService;

public class ContaForeignKeyGerenteValidator implements ConstraintValidator<ContaForeignKeyGerente, Integer>{
    @Autowired
    private ContaService service; 

<<<<<<< HEAD
	@Override
	public boolean isValid(Integer codigoGerente, ConstraintValidatorContext context) {
		return service.isGerentePresent(codigoGerente);
	}
=======
    @Override
    public boolean isValid(Integer codigoGerente, ConstraintValidatorContext context) {
    //    return !service.cnpjJaExiste(cnpj);
        return true;
    }
>>>>>>> 4ed0effe734043d395a8bb3569dd9566152f4372

}
