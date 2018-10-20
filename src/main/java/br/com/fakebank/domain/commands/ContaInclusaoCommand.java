package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.fakebank.domain.Cliente;

public class ContaInclusaoCommand {

	@NotNull @NotBlank	
	private String codigoConta;	
	@NotNull
	private Cliente cliente;
	@NotNull
	private Integer tipoConta;
	@NotNull
	private Integer codigoGerente;
	
	private String numeroCnpjContratoSalario;
	
	public ContaInclusaoCommand () {
		
	}	

	public String getCodigoConta() {
		return codigoConta;
	}

	public void setCodigoConta(String codigoConta) {
		this.codigoConta = codigoConta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(Integer tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Integer getCodigoGerente() {
		return codigoGerente;
	}

	public void setCodigoGerente(Integer codigoGerente) {
		this.codigoGerente = codigoGerente;
	}

	public String getNumeroCnpjContratoSalario() {
		return numeroCnpjContratoSalario;
	}

	public void setNumeroCnpjContratoSalario(String numeroCnpjContratoSalario) {
		this.numeroCnpjContratoSalario = numeroCnpjContratoSalario;
	}
	
	
	
	
}
