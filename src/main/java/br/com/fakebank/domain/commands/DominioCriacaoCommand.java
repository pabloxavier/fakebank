package br.com.fakebank.domain.commands;

import br.com.fakebank.customValidators.DominioCodigoValid;
import br.com.fakebank.customValidators.DominioDescricaoValid;
import br.com.fakebank.domain.validators.CommandValidator;

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
