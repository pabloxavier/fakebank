package br.com.fakebank.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.fakebank.domain.commands.DominioCriacaoCommand;

@Entity
@DiscriminatorValue("sit_conta")
public class SituacaoConta extends Dominio{
	
	public SituacaoConta() {
		
	}

	private SituacaoConta(DominioCriacaoCommand comando) {

		this.setTipo(comando.getTipo());
		this.setValor(comando.getValor());
		this.setDescricao(comando.getDescricao());

	}

	public static SituacaoConta criar(DominioCriacaoCommand comando) {

		return new SituacaoConta(comando);

	}
	
}