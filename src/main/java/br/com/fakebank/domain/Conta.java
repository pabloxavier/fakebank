package br.com.fakebank.domain;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.fakebank.domain.commands.ContaInclusaoCommand;

@Entity
@Table(name = "CONTA", schema = "dbo")
public class Conta {
	
	@Id @GeneratedValue
	@Column(name = "CD_CONTA")
	private String codigoConta;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CD_CLIENTE_PRINCIPAL")
	private Cliente cliente;
	
	@Column(name = "DT_ABERTURA")
	private Date dataAbertura;
	
	@Column(name = "TP_CONTA")
	private Integer tipoConta;
	
	@Column(name = "CD_GERENTE")
	private Integer codigoGerente;
	
	@Column(name = "CD_SITUACAO_CONTA")
	private Integer codigoSituacaoConta;
	
	@Column(name = "VL_SALDO")
	private Double valorSaldo;
	
	@Column(name = "NR_CNPJ_CONTRATO_SALARIO")
	private String numeroCnpjContratoSalario;
	
	@Column(name ="DD_ANIVERSARIO_POUPANCA")
	private Integer diaAniversarioPoupanca;
	
	protected Conta() {
		
	}
	
	public static Conta criar(ContaInclusaoCommand command) {
		return new Conta(command);
	}
	
	private Conta (ContaInclusaoCommand command) {
		this.codigoConta = command.getCodigoConta();
		this.codigoGerente = command.getCodigoGerente();
		this.codigoSituacaoConta = 10;
		this.numeroCnpjContratoSalario = command.getNumeroCnpjContratoSalario();
	}
		
	public String getCodigoConta() {
		return codigoConta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public Integer getTipoConta() {
		return tipoConta;
	}

	public Integer getCodigoGerente() {
		return codigoGerente;
	}

	public Integer getCodigoSituacaoConta() {
		return codigoSituacaoConta;
	}

	public Double getValorSaldo() {
		return valorSaldo;
	}

	public String getNumeroCnpjContratoSalario() {
		return numeroCnpjContratoSalario;
	}

	public Integer getDiaAniversarioPoupanca() {
		return diaAniversarioPoupanca;
	}
	
}
