package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotNull;

public class ContaSalarioEdicaoCommand {
    
    @NotNull
    private Integer codigoGerente;
    
    private Integer codigoSituacaoConta;
    
    private String numeroCnpjContratoSalario;
    
    public ContaSalarioEdicaoCommand(){
        
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

    public String getNumeroCnpjContratoSalario() {
        return numeroCnpjContratoSalario;
    }

    public void setNumeroCnpjContratoSalario(String numeroCnpjContratoSalario) {
        this.numeroCnpjContratoSalario = numeroCnpjContratoSalario;
    }


}
