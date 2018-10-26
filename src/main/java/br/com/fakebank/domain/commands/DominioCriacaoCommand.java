package br.com.fakebank.domain.commands;

public class DominioCriacaoCommand {

	private String valor;

	private String descricao;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
