package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotNull;

public class ContaPoupancaEdicaoCommand {
	
	@NotNull
	private Integer codigoGerente;
	
	private Integer codigoSituacaoConta;
	
	private Double valorSaldo;
	
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

	public Double getValorSaldo() {
		return valorSaldo;
	}

	public void setValorSaldo(Double valorSaldo) {
		this.valorSaldo = valorSaldo;
	}

	public Integer getDiaAniversarioPoupanca() {
		return diaAniversarioPoupanca;
	}

	public void setDiaAniversarioPoupanca(Integer diaAniversarioPoupanca) {
		this.diaAniversarioPoupanca = diaAniversarioPoupanca;
	}

	
}
