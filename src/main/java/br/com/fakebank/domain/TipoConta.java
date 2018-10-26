package br.com.fakebank.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.fakebank.domain.commands.DominioCriacaoCommand;

@Entity
@DiscriminatorValue("tipo_conta")
public class TipoConta extends Dominio{

	
	public TipoConta() {
		
	}
	
	private TipoConta(DominioCriacaoCommand comando) {

		this.setTipo(comando.getTipo());
		this.setValor(comando.getValor());
		this.setDescricao(comando.getDescricao());

	}

	public static TipoConta criar(DominioCriacaoCommand comando) {

		return new TipoConta(comando);

	}
	
}
