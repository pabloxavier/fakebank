package br.com.fakebank.domain.commands;

import br.com.fakebank.domain.Pessoa;

public class GerenteEdicaoCommand {

	private boolean isAtivo;
	private Pessoa pessoa;

	protected GerenteEdicaoCommand() {
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}