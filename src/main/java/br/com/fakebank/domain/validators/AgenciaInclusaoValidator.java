package br.com.fakebank.domain.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.fakebank.domain.commands.AgenciaInclusaoCommand;
import br.com.fakebank.exceptions.MessageErrorDetail;
import br.com.fakebank.exceptions.RequisicaoMalFormada;

public class AgenciaInclusaoValidator extends AbstractValidator {

    public void validate(AgenciaInclusaoCommand command) {
    	
        List<MessageErrorDetail> violacoes =
        		new ArrayList<MessageErrorDetail>();

        ValidatorFactory factory =
        		Validation.buildDefaultValidatorFactory();
        
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<AgenciaInclusaoCommand>>
        	errosEncontrados =
                validator.validate(command);
        
        for (ConstraintViolation<AgenciaInclusaoCommand> error : errosEncontrados) {
            setRule(error);
            violacoes.add(
            		new MessageErrorDetail(
            				getFieldError(),
            				getMessageError()));
        }

        if (!violacoes.isEmpty()) {
            throw new RequisicaoMalFormada(violacoes);
        }
    }
}
