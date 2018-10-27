package br.com.fakebank.domain.commands;

import br.com.fakebank.domain.TipoTelefone;

public class ClienteTelefoneEdicaoCommand {
	
	private Short prefixo;
	private Integer telefone; 
	private TipoTelefone tipoTelefone;
	
	protected ClienteTelefoneEdicaoCommand() {
		
	}

	public Short getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(Short prefixo) {
		this.prefixo = prefixo;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

}
