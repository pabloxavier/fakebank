package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContaSalarioInclusaoCommand {
	@NotNull @NotBlank	
	private String codigoConta;	
	@NotNull
	private Integer codigoGerente;
	
	private String numeroCnpjContratoSalario;
	
	public ContaSalarioInclusaoCommand () {
		
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

	public String getNumeroCnpjContratoSalario() {
		return numeroCnpjContratoSalario;
	}

	public void setNumeroCnpjContratoSalario(String numeroCnpjContratoSalario) {
		this.numeroCnpjContratoSalario = numeroCnpjContratoSalario;
	}
}


