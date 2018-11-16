package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotNull;

import br.com.fakebank.customValidators.ContaForeignKeyGerente;
import br.com.fakebank.domain.validators.CommandValidator;
import br.com.fakebank.exceptions.FieldName;

public class ContaCorrenteInclusaoCommand extends ContaInclusaoCommand{

	    
	    public ContaCorrenteInclusaoCommand () {
	    		        
	    }
	    
	    public void validate() {
	    	CommandValidator<ContaCorrenteInclusaoCommand> validator =
	        		new CommandValidator<ContaCorrenteInclusaoCommand>();
	        validator.validate(this);
	    }
	    

	}	
