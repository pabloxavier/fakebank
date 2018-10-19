package br.com.fakebank.domain.commands;

import br.com.fakebank.domain.Pessoa;

public class ClienteEdicaoCommand {

	private Pessoa pessoa;
	private boolean isAtivo;
	private String endereco;

	protected ClienteEdicaoCommand(){
		
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
