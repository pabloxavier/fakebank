package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContaCorrenteInclusaoCommand {

	@NotNull @NotBlank	
	private String codigoConta;	
	@NotNull
	private Integer codigoGerente;
	
	public ContaCorrenteInclusaoCommand () {
		
	}	

	public String getCodigoConta() {
		return codigoConta;
	}

	public void setCodigoConta(String codigoConta) {
		this.codigoConta = codigoConta;
	}

	public Integer getCodigoGerente() {
		return codigoGerente;
	}

	public void setCodigoGerente(Integer codigoGerente) {
		this.codigoGerente = codigoGerente;
	}

}
