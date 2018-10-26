package br.com.fakebank.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "movimentacao", schema = "dbo")
public class Movimentacao {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_movimentacao")
	private Integer codigoMovimentacao;
	
	@NotNull
	@NotBlank
	@Column(name = "cd_conta")
	private Integer codigoConta;

	@NotNull
	@NotBlank
	@Column(name = "dt_movimentacao")
	private LocalDate dataMovimentacao;
	
	@NotNull
	@NotBlank
	@Column(name = "vl_movimentacao")
	private double valorMovimentacao;

	@NotNull
	@NotBlank
	@Column(name = "cd_tipo_movimentacao")
	private Integer codigoTipoMovimentacao;
	
	@NotNull
	@NotBlank
	@Column(name = "vl_saldo_anterior")
	private double valorSaldoAnterior;
	
	@NotNull
	@NotBlank
	@Column(name = "vl_saldo_atual")
	private double valorSaldoAtual;
	
	public Integer getCodigoMovimentacao() {
		return codigoMovimentacao;
	}

	public void setCodigoMovimentacao(Integer codigoMovimentacao) {
		this.codigoMovimentacao = codigoMovimentacao;
	}

	public Integer getCodigoConta() {
		return codigoConta;
	}

	public void setCodigoConta(Integer codigoConta) {
		this.codigoConta = codigoConta;
	}

	public LocalDate getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(LocalDate dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public double getValorMovimentacao() {
		return valorMovimentacao;
	}

	public void setValorMovimentacao(double valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}

	public Integer getCodigoTipoMovimentacao() {
		return codigoTipoMovimentacao;
	}

	public void setCodigoTipoMovimentacao(Integer codigoTipoMovimentacao) {
		this.codigoTipoMovimentacao = codigoTipoMovimentacao;
	}

	public double getValorSaldoAnterior() {
		return valorSaldoAnterior;
	}

	public void setValorSaldoAnterior(double valorSaldoAnterior) {
		this.valorSaldoAnterior = valorSaldoAnterior;
	}

	public double getValorSaldoAtual() {
		return valorSaldoAtual;
	}

	public void setValorSaldoAtual(double valorSaldoAtual) {
		this.valorSaldoAtual = valorSaldoAtual;
	}
}
