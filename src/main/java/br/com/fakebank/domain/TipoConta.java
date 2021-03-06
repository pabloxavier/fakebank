package br.com.fakebank.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.fakebank.domain.commands.DominioCriacaoCommand;
import br.com.fakebank.domain.commands.DominioEdicaoCommand;

@Entity
@DiscriminatorValue("tipo_conta")
public class TipoConta extends Dominio{

    
    public TipoConta() {
        
    }
    
    private TipoConta(DominioCriacaoCommand comando) {
        this.setTipo(DominioEnum.TIPO_CONTA.toString());
        this.setValor(comando.getCodigo().toString());
        this.setDescricao(comando.getDescricao());
    }

    public static TipoConta criar(DominioCriacaoCommand comando) {
    	comando.validate();
        return new TipoConta(comando);
    }

    public void editar(DominioEdicaoCommand comando) {
    	comando.validate();
        this.setDescricao(comando.getDescricao());
    }

}
