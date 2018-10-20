package br.com.fakebank.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
	@Column(name = "cd_conta")
	private Integer codigoConta;
	
	@NotNull
	@Column(name = "dt_movimentacao")
	private LocalDate dataMovimentacao;
	
	@NotNull
	@Column(name = "vl_movimentacao")
	private double valorMovimentacao;

	@NotNull
	@Column(name = "cd_tipo_movimentacao")
	private Integer codigoTipoMovimentacao;
	
	@NotNull
	@Column(name = "vl_saldo_anterior")
	private double valorSaldoAnterior;
	
	@NotNull
	@Column(name = "vl_saldo_atual")
	private double valorSaldoAtual;
}
