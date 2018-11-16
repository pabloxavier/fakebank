package br.com.fakebank.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.fakebank.domain.commands.DominioCriacaoCommand;
import br.com.fakebank.domain.commands.DominioEdicaoCommand;

@Entity
@DiscriminatorValue("motivo")
public class MotivoEncerramento extends Dominio{
    
    public MotivoEncerramento() {
        
    }

    private MotivoEncerramento(DominioCriacaoCommand comando) {
        this.setValor(comando.getCodigo().toString());
        this.setDescricao(comando.getDescricao());

    }

    public static MotivoEncerramento criar(DominioCriacaoCommand comando) {
        return new MotivoEncerramento(comando);

    }
    
    public void editar(DominioEdicaoCommand comando) {
 		this.setDescricao(comando.getDescricao());
	}

}