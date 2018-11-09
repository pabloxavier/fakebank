package br.com.fakebank.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.fakebank.domain.commands.DominioCriacaoCommand;
import br.com.fakebank.domain.commands.DominioEdicaoCommand;

@Entity
@DiscriminatorValue("sit_conta")
public class SituacaoConta extends Dominio{
    
    public SituacaoConta() {
        
    }

    private SituacaoConta(DominioCriacaoCommand comando) {

        this.setTipo(DominioEnum.SIT_CONTA.toString());
        this.setValor(comando.getValor());
        this.setDescricao(comando.getDescricao());

    }

    public static SituacaoConta criar(DominioCriacaoCommand comando) {
    	comando.validate();
        return new SituacaoConta(comando);

    }
    
    public void editar(DominioEdicaoCommand comando) {
    	comando.validate();
        this.setDescricao(comando.getDescricao());
    }
    
}