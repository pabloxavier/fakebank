package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContaCorrenteInclusaoCommand {

    @NotNull
    private Integer codigoGerente;
    
    public ContaCorrenteInclusaoCommand () {
        
    }

    public Integer getCodigoGerente() {
        return codigoGerente;
    }

    public void setCodigoGerente(Integer codigoGerente) {
        this.codigoGerente = codigoGerente;
    }

}
