package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotNull;

import br.com.fakebank.domain.validators.CommandValidator;
import br.com.fakebank.exceptions.FieldName;

public abstract class ContaInclusaoCommand {

    @NotNull
    @FieldName("gerente")
    private Integer codigoGerente;
    

    public Integer getCodigoGerente() {
        return codigoGerente;
    }

    public void setCodigoGerente(Integer codigoGerente) {
        this.codigoGerente = codigoGerente;
    }
    
    public void validate() {
    	CommandValidator<ContaInclusaoCommand> validator =
        		new CommandValidator<ContaInclusaoCommand>();
        validator.validate(this);
    }
}
