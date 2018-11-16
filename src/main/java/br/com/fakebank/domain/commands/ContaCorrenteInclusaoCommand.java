package br.com.fakebank.domain.commands;

import br.com.fakebank.domain.validators.CommandValidator;

public class ContaCorrenteInclusaoCommand extends ContaInclusaoCommand{
	    
	    public ContaCorrenteInclusaoCommand () {
	    		        
	    }
	    
	    public void validate() {
	    	CommandValidator<ContaCorrenteInclusaoCommand> validator =
	        		new CommandValidator<ContaCorrenteInclusaoCommand>();
	        validator.validate(this);
	    }
	    

	}	
