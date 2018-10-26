package br.com.fakebank.domain.commands;

public class MovimentacaoSaqueCommand {

	private double valor;

	private Integer conta;

	public MovimentacaoSaqueCommand() {
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}
}
