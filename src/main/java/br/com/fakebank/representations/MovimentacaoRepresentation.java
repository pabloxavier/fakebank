package br.com.fakebank.representations;

import java.time.LocalDate;

import br.com.fakebank.domain.Agencia;
import br.com.fakebank.domain.Movimentacao;

public class MovimentacaoRepresentation {

	private Integer codigoMovimentacao;
	private Integer codigoConta;
	private LocalDate dataMovimentacao;
	private double valorMovimentacao;
	private Integer codigoTipoMovimentacao;
	private double valorSaldoAtual;

	public static MovimentacaoRepresentation from(Movimentacao movimentacao) {
		MovimentacaoRepresentation model = new MovimentacaoRepresentation();
		model.setCodigoMovimentacao(movimentacao.getCodigoMovimentacao());
		model.setCodigoConta(movimentacao.getCodigoConta());
		model.setDataMovimentacao(movimentacao.getDataMovimentacao());
		model.setValorMovimentacao(movimentacao.getValorMovimentacao());
		model.setCodigoTipoMovimentacao(movimentacao.getCodigoTipoMovimentacao());
		model.setValorSaldoAtual(movimentacao.getValorSaldoAtual());
		return model;
	}

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

	public double getValorSaldoAtual() {
		return valorSaldoAtual;
	}

	public void setValorSaldoAtual(double valorSaldoAtual) {
		this.valorSaldoAtual = valorSaldoAtual;
	}
}
