package br.com.fakebank.domain.commands;

public class MovimentacaoTransferenciaCommand {

	private double valor;

	private Integer contaOrigem;

	private Integer contaDestino;

	public MovimentacaoTransferenciaCommand() {
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Integer getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Integer contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Integer getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Integer contaDestino) {
		this.contaDestino = contaDestino;
	}

}
