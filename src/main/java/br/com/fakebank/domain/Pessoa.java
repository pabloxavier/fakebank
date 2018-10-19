package br.com.fakebank.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa", schema = "dbo")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_pessoa")
	private Integer codigo;

	@Column(name = "nr_documento")
	private String documento;
	
	@Column(name = "tp_pessoa")
	private String tipoPessoa;
	
	@Column(name = "nm_pessoa")
	private String nome;
	
	@Column(name = "dt_nascimento")
	private LocalDate dataNascimento;
	
	@Column(name = "dt_abertura")
	private LocalDate dataAbertura;

	protected Pessoa(){
		
	}
	
	private Pessoa(Integer codigo, String documento, String tipoPessoa, String nome, LocalDate dataNascimento,
			LocalDate dataAbertura) {
		this.codigo = codigo;
		this.documento = documento;
		this.tipoPessoa = tipoPessoa;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.dataAbertura = dataAbertura;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDocumento() {
		return documento;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}
	
	
}
