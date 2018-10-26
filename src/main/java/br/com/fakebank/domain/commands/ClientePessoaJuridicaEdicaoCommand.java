package br.com.fakebank.domain.commands;

public class ClientePessoaJuridicaEdicaoCommand {
	
	private boolean isAtivo;
	private String endereco;

	public ClientePessoaJuridicaEdicaoCommand() {
		
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
