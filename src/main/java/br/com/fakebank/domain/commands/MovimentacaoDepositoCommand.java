package br.com.fakebank.domain.commands;

public class MovimentacaoDepositoCommand {

	private double valor;
	
	private String conta;
	
	public MovimentacaoDepositoCommand() {
		
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}
}
