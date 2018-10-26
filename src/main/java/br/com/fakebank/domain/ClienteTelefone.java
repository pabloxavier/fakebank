package br.com.fakebank.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "cliente_telefone", schema= "dbo")
public class ClienteTelefone implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name = "cd_cliente")
	private Cliente cliente;
	
	@Id
	@Column(name= "cd_telefone")
	private Short codigo;
	
	@Column(name= "nr_prefixo")
	private Short prefixo;
	
	@Column(name= "nr_telefone")
	private Integer numero;
	
	@Column(name = "cd_tipo_telefone")
	private TipoTelefone tipoTelefone;
	
	public ClienteTelefone() {
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Short getCodigo() {
		return codigo;
	}

	public Short getPrefixo() {
		return prefixo;
	}

	public Integer getNumero() {
		return numero;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

}
