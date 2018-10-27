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
import br.com.fakebank.exceptions.BadRequestException;

public class CommandValidator<T> extends AbstractValidator {

    public void validate(T command) {
    
        List<MessageErrorDetail> violacoes =
                new ArrayList<MessageErrorDetail>();

        ValidatorFactory factoryValidator =
                Validation.buildDefaultValidatorFactory();
        
        Validator validator =
                factoryValidator.getValidator();

        Set<ConstraintViolation<T>>
            errosEncontrados =
                validator.validate(command);
        
        for (ConstraintViolation<T> error : errosEncontrados) {
        	
            setRule(error);
            
            violacoes.add(
                    new MessageErrorDetail(
                            getFieldError(),
                            getMessageError()));
        }

        if (!violacoes.isEmpty()) {
            throw new BadRequestException(violacoes);
        }
    }
}
