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

import br.com.fakebank.domain.commands.ClienteEdicaoCommand;
import br.com.fakebank.domain.commands.ClienteInclusaoCommand;

@Entity
@Table(name = "cliente", schema = "dbo")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_cliente")
	private Integer codigo;
	
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cd_pessoa")
	private Pessoa pessoa;
	
	@Column(name = "is_ativo")
	private boolean isAtivo;
	
	@Column(name = "ds_endereco_completo")
	private String endereco;

	protected Cliente(){
		
	}
	
	private Cliente(ClienteInclusaoCommand comando){
		this.isAtivo = comando.isAtivo();
		this.endereco = comando.getEndereco();
		this.pessoa = comando.getPessoa();
	}
	
	public static Cliente criar(ClienteInclusaoCommand comando){
		return new Cliente(comando);
	}
	
	public void editar(ClienteEdicaoCommand comando){
		this.endereco = comando.getEndereco();
		this.isAtivo = comando.isAtivo();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public String getEndereco() {
		return endereco;
	}
	
	
}