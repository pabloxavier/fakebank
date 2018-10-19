package br.com.fakebank.domain.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.fakebank.customValidators.AgenciaUniqueCnpj;
import br.com.fakebank.customValidators.CnpjValid;

public class AgenciaInclusaoCommand {

	@NotNull
	@Range(min = 1, max = 9999)
	private Integer numero;
	
	@NotNull(message = "{agencia.nome.nao.nulo}")
	@NotBlank(message = "{agencia.nome.nao.vazio}")
	private String nome;
	
	@NotNull
	@NotBlank
	@CnpjValid
	@AgenciaUniqueCnpj
	private String cnpj;
	
	public AgenciaInclusaoCommand(){
		
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
}