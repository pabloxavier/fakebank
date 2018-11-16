package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotNull;

import br.com.fakebank.common.validations.CommandValidator;

public class ContaPoupancaEdicaoCommand {
    
    @NotNull
    private Integer codigoGerente;
    
    private Integer codigoSituacaoConta;
    
    private Integer diaAniversarioPoupanca;
    
    public ContaPoupancaEdicaoCommand(){
        
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

    public Integer getDiaAniversarioPoupanca() {
        return diaAniversarioPoupanca;
    }

    public void setDiaAniversarioPoupanca(Integer diaAniversarioPoupanca) {
        this.diaAniversarioPoupanca = diaAniversarioPoupanca;
    }
    
    public void validate() {
    	CommandValidator<ContaPoupancaEdicaoCommand> validator =
        		new CommandValidator<ContaPoupancaEdicaoCommand>();
        validator.validate(this);
    }

}
