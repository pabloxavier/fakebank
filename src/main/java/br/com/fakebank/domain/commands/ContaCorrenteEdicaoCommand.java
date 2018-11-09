package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotNull;

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

}
