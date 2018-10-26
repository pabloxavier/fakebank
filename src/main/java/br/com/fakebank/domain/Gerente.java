package br.com.fakebank.domain;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
 @Entity
@Table(name = "GERENTE", schema = "dbo")
public class Gerente {
 	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_GERENTE")
	private Integer CodGerente;
	
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CD_PESSOA")
	private Pessoa pessoa;
	
	@Column(name = "IS_ATIVO")
	private boolean isAtivo;
 	public Integer getCodGerente() {
		return CodGerente;
	}
 	public Pessoa getPessoa() {
		return pessoa;
	}
 	public boolean isAtivo() {
		return isAtivo;
	}
	
}