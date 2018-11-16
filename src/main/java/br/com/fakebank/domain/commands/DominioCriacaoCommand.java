package br.com.fakebank.domain.commands;

import br.com.fakebank.common.validations.CommandValidator;
import br.com.fakebank.domain.validators.DominioCodigoValid;
import br.com.fakebank.domain.validators.DominioDescricaoValid;

public class DominioCriacaoCommand {
	
	@DominioCodigoValid
	private Integer codigo;
    
    @DominioDescricaoValid
    private String descricao;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void validate() {
    	CommandValidator<DominioCriacaoCommand> validator  = new CommandValidator<DominioCriacaoCommand>();
        validator.validate(this);
    }
}
