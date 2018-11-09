package br.com.fakebank.domain.commands;

import br.com.fakebank.customValidators.DominioDescricaoValid;
import br.com.fakebank.domain.validators.CommandValidator;

public class DominioEdicaoCommand {

	@DominioDescricaoValid
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void validate() {
    	CommandValidator<DominioEdicaoCommand> validator = new CommandValidator<DominioEdicaoCommand>();
        validator.validate(this);
    }

}
