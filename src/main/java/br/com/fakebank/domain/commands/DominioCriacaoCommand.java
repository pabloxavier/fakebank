package br.com.fakebank.domain.commands;

import br.com.fakebank.customValidators.DominioDescricaoValid;
import br.com.fakebank.domain.validators.CommandValidator;

public class DominioCriacaoCommand {

    private String valor;
    
    @DominioDescricaoValid
    private String descricao;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void validate() {
    	CommandValidator<DominioCriacaoCommand> validator = new CommandValidator<DominioCriacaoCommand>();
        validator.validate(this);
    }

}
