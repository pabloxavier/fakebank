package br.com.fakebank.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fakebank.domain.commands.MovimentacaoDepositoCommand;
import br.com.fakebank.domain.commands.MovimentacaoSaqueCommand;
import br.com.fakebank.repository.ContaRepository;

@Entity
@Table(name = "movimentacao", schema = "dbo")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_movimentacao")
    private Integer codigoMovimentacao;
    
    @ManyToOne
    @JoinColumn(name = "cd_conta")
    private Conta codigoConta;

    @Column(name = "dt_movimentacao")
    private LocalDate dataMovimentacao;
    
    @Column(name = "vl_movimentacao")
    private Double valorMovimentacao;

    @Column(name = "cd_tipo_movimentacao")
    private Integer codigoTipoMovimentacao;
    
    @Column(name = "vl_saldo_anterior")
    private Double valorSaldoAnterior;
    
    @Column(name = "vl_saldo_atual")
    private Double valorSaldoAtual;
    
    @Transient
    @Autowired
    private ContaRepository contaRepository;
    
    protected Movimentacao(String conta, LocalDate data, Integer tipoMovimentacao, 
            Double saldoAnterior, Double saldoAtual, Double valorMovimentacao) {
        this.codigoConta = getContaByNumero(conta);
        this.dataMovimentacao = data;
        this.codigoTipoMovimentacao = tipoMovimentacao;
        this.valorSaldoAnterior = saldoAnterior;
        this.valorSaldoAtual = saldoAtual;
        this.valorMovimentacao = valorMovimentacao;
    }
    
    protected Movimentacao() {}
    
    public static Movimentacao criar(MovimentacaoDepositoCommand comando, Double saldoAnterior, Double saldoAtual) {
        return new Movimentacao(comando.getConta(), LocalDate.now(), 1, saldoAnterior, saldoAtual, comando.getValor());
    }
    
    public static Movimentacao criar(MovimentacaoSaqueCommand comando, Double saldoAnterior, Double saldoAtual) {
        return new Movimentacao(comando.getConta(), LocalDate.now(), 1, saldoAnterior, saldoAtual, comando.getValor());
    }
    
    protected Movimentacao(String conta, Double valor) {
        this.codigoConta = getContaByNumero(conta);
        this.valorMovimentacao = valor;
    }
    
    public static Movimentacao criar(String conta, Double valor) {
        return new Movimentacao(conta, valor);
    }
    
    public Integer getCodigoMovimentacao() {
        return codigoMovimentacao;
    }

    public void setCodigoMovimentacao(Integer codigoMovimentacao) {
        this.codigoMovimentacao = codigoMovimentacao;
    }

    public Conta getCodigoConta() {
        return codigoConta;
    }

    public void setCodigoConta(Conta codigoConta) {
        this.codigoConta = codigoConta;
    }

    public LocalDate getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDate dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Double getValorMovimentacao() {
        return valorMovimentacao;
    }

    public void setValorMovimentacao(Double valorMovimentacao) {
        this.valorMovimentacao = valorMovimentacao;
    }

    public Integer getCodigoTipoMovimentacao() {
        return codigoTipoMovimentacao;
    }

    public void setCodigoTipoMovimentacao(Integer codigoTipoMovimentacao) {
        this.codigoTipoMovimentacao = codigoTipoMovimentacao;
    }

    public Double getValorSaldoAnterior() {
        return valorSaldoAnterior;
    }

    public void setValorSaldoAnterior(Double valorSaldoAnterior) {
        this.valorSaldoAnterior = valorSaldoAnterior;
    }

    public Double getValorSaldoAtual() {
        return valorSaldoAtual;
    }

    public void setValorSaldoAtual(Double valorSaldoAtual) {
        this.valorSaldoAtual = valorSaldoAtual;
    }
    
    private Conta getContaByNumero(String codigoConta) {
        return contaRepository.findById(codigoConta).orElse(null);
    }
}
