package br.com.fakebank.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import br.com.fakebank.ApplicationContextProvider;
import br.com.fakebank.service.AgenciaService;

public class AgenciaUniqueCnpjValidator implements ConstraintValidator<AgenciaUniqueCnpj, String>{

    @Autowired
    private ApplicationContext applicationContext;

    private AgenciaService service;
    
    @Override
    public void initialize(AgenciaUniqueCnpj unique) {
        this.service = ApplicationContextProvider.getBean(AgenciaService.class);
    }
    
    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        return !service.cnpjJaExiste(cnpj);
    }

}
