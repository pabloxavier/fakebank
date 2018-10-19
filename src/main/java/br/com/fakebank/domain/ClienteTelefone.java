package br.com.fakebank.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "cliente_telefone", schema= "dbo")
public class ClienteTelefone {
	
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


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Short getCodigo() {
		return codigo;
	}

	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	public Short getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(Short prefixo) {
		this.prefixo = prefixo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

}
