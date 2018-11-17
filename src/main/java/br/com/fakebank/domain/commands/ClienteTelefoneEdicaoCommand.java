package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotNull;

import br.com.fakebank.common.validations.CommandValidator;
import br.com.fakebank.common.validations.FieldName;
import br.com.fakebank.domain.TipoTelefone;

public class ClienteTelefoneEdicaoCommand {
	
	@FieldName("prefixo")
	@NotNull
	private Short prefixo;
	
	@FieldName("telefone")
	@NotNull
	private Integer telefone; 
	
	@FieldName("tipoTelefone")
	@NotNull
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

	public void validate() {
		CommandValidator<ClienteTelefoneEdicaoCommand> validator = 
				new CommandValidator<>();
		validator.validate(this);
	}
	
}
