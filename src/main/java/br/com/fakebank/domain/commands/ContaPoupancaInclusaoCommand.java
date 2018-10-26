package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContaPoupancaInclusaoCommand {

	@NotNull
	private Integer codigoGerente;
	
	private Integer diaAniversarioPoupanca;	
	
	public ContaPoupancaInclusaoCommand () {
		
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
