package br.com.fakebank.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.fakebank.domain.commands.DominioCriacaoCommand;

@Entity
@DiscriminatorValue("motivo")
public class MotivoEncerramento extends Dominio{
	
	public MotivoEncerramento() {
		
	}

	private MotivoEncerramento(DominioCriacaoCommand comando) {

		this.setTipo(comando.getTipo());
		this.setValor(comando.getValor());
		this.setDescricao(comando.getDescricao());

	}

	public static MotivoEncerramento criar(DominioCriacaoCommand comando) {

		return new MotivoEncerramento(comando);

	}
	
}