package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContaPoupancaInclusaoCommand {
	@NotNull @NotBlank	
	private String codigoConta;	
	@NotNull
	private Integer codigoGerente;
	
	private Integer diaAniversarioPoupanca;	
	
	public ContaPoupancaInclusaoCommand () {
		
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

	public Integer getDiaAniversarioPoupanca() {
		return diaAniversarioPoupanca;
	}

	public void setDiaAniversarioPoupanca(Integer diaAniversarioPoupanca) {
		this.diaAniversarioPoupanca = diaAniversarioPoupanca;
	}

}
