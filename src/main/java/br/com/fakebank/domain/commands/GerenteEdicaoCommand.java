package br.com.fakebank.domain.commands;

public class GerenteEdicaoCommand {
	
	private boolean isAtivo;
	private String pessoa;
	
	protected GerenteEdicaoCommand() {}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public String getPessoa() {
		return pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}
}
