package br.com.fakebank.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.fakebank.domain.commands.GerenteAgenciaInclusaoCommand;
import br.com.fakebank.domain.commands.GerenteInclusaoCommand;

@Entity
@Table(name = "GERENTE_AGENCIA", schema = "dbo")
public class GerenteAgencia {
	    
	@ManyToMany(cascade = CascadeType.PERSIST)
    @Column(name = "CD_GERENTE")
    private Integer codGerente;

	@ManyToMany(cascade = CascadeType.PERSIST)
    @Column(name = "CD_AGENCIA")
    private Integer codAgencia;
	
	protected GerenteAgencia() {}
	
	public Integer getCodGerente() {
		return codGerente;
	}
	
	public Integer getCodAgencia() {
		return codAgencia;
	}
}
