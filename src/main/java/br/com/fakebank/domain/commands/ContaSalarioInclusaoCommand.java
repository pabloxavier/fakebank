package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.fakebank.customValidators.CnpjValid;
import br.com.fakebank.customValidators.ContaForeignKeyGerente;
import br.com.fakebank.domain.validators.CommandValidator;
import br.com.fakebank.exceptions.FieldName;

public class ContaSalarioInclusaoCommand extends ContaInclusaoCommand{
	
	@CnpjValid
	@NotNull
	@FieldName("Cnpj")
    private String numeroCnpjContratoSalario;
    
    public ContaSalarioInclusaoCommand () {
    	super();
    }    

    public String getNumeroCnpjContratoSalario() {
        return numeroCnpjContratoSalario;
    }

    public void setNumeroCnpjContratoSalario(String numeroCnpjContratoSalario) {
        this.numeroCnpjContratoSalario = numeroCnpjContratoSalario;
    }
    
    public void validate() {
    	CommandValidator<ContaSalarioInclusaoCommand> validator =
        		new CommandValidator<ContaSalarioInclusaoCommand>();
        validator.validate(this);
    }
    
    
}


