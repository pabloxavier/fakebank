package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.fakebank.customValidators.ContaForeignKeyGerente;
import br.com.fakebank.domain.validators.CommandValidator;

public abstract class ContaCorrenteInclusaoCommand {

    @NotNull
    @ContaForeignKeyGerente
    private Integer codigoGerente;
    
    public ContaCorrenteInclusaoCommand () {
        
    }


    public Integer getCodigoGerente() {
        return codigoGerente;
    }

    public void setCodigoGerente(Integer codigoGerente) {
        this.codigoGerente = codigoGerente;
    }
    
    public void validate() {
    	CommandValidator<ContaCorrenteInclusaoCommand> validator =
        		new CommandValidator<ContaCorrenteInclusaoCommand>();
        validator.validate(this);
    }
    

}
