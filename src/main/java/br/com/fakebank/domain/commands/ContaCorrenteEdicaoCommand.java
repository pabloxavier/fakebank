package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotNull;

import br.com.fakebank.common.validations.CommandValidator;

public class ContaCorrenteEdicaoCommand {

    @NotNull
    private Integer codigoGerente;
    
    private Integer codigoSituacaoConta;
    
    public ContaCorrenteEdicaoCommand(){
        
    }

    public Integer getCodigoGerente() {
        return codigoGerente;
    }

    public void setCodigoGerente(Integer codigoGerente) {
        this.codigoGerente = codigoGerente;
    }

    public Integer getCodigoSituacaoConta() {
        return codigoSituacaoConta;
    }

    public void setCodigoSituacaoConta(Integer codigoSituacaoConta) {
        this.codigoSituacaoConta = codigoSituacaoConta;
    }
    
    public void validate() {
    	CommandValidator<ContaCorrenteEdicaoCommand> validator =
        		new CommandValidator<ContaCorrenteEdicaoCommand>();
        validator.validate(this);
    }

}
