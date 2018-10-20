package br.com.fakebank.domain.commands;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.fakebank.customValidators.CnpjValid;

public class ClientePessoaJuridicaInclusaoCommand {
	
	@NotNull
	@NotBlank
	@CnpjValid
	private String cnpj;
	
	@NotNull(message = "{clientepj.nome.nao.nulo}")
	@NotBlank(message = "{clientepj.nome.nao.vazio}")
	private String nome;
	
	private Date dataAbertura;
	private String enderecoCompleto;
	
	public ClientePessoaJuridicaInclusaoCommand() {
		
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}
	
	

}
