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
	
	@FieldName("tipo")
	@NotNull
	private TipoTelefone tipo;
	
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

	public TipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}

	public void validate() {
		CommandValidator<ClienteTelefoneEdicaoCommand> validator = 
				new CommandValidator<>();
		validator.validate(this);
	}
	
}
