package br.com.fakebank.domain;

import java.sql.Date;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.fakebank.customValidators.ContaForeignKeyClientePrincipal;
import br.com.fakebank.customValidators.ContaForeignKeyGerente;
import br.com.fakebank.customValidators.ContaForeignKeySituacaoConta;
import br.com.fakebank.customValidators.ContaForeignKeyTipoConta;
import br.com.fakebank.domain.commands.ContaCorrenteEdicaoCommand;
import br.com.fakebank.domain.commands.ContaCorrenteInclusaoCommand;
import br.com.fakebank.domain.commands.ContaPoupancaEdicaoCommand;
import br.com.fakebank.domain.commands.ContaPoupancaInclusaoCommand;
import br.com.fakebank.domain.commands.ContaSalarioEdicaoCommand;
import br.com.fakebank.domain.commands.ContaSalarioInclusaoCommand;
import br.com.fakebank.util.DateUtil;

@Entity
@Table(name = "CONTA", schema = "dbo")
public class Conta {
    
    @Id @GeneratedValue
    @Column(name = "CD_CONTA")
    private String codigoConta;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CD_CLIENTE_PRINCIPAL")
    @ContaForeignKeyClientePrincipal
    private Cliente cliente;
    
    @Column(name = "DT_ABERTURA")
    private Date dataAbertura;
    
    @Column(name = "TP_CONTA")
    @ContaForeignKeyTipoConta
    private Integer tipoConta;
    
    @Column(name = "CD_GERENTE")
    @ContaForeignKeyGerente
    private Integer codigoGerente;
    
    @Column(name = "CD_SITUACAO_CONTA")
    @ContaForeignKeySituacaoConta
    private Integer codigoSituacaoConta;
    
    @Column(name = "VL_SALDO")
    private Double valorSaldo;
    
    @Column(name = "NR_CNPJ_CONTRATO_SALARIO")
    private String numeroCnpjContratoSalario;
    
    @Column(name ="DD_ANIVERSARIO_POUPANCA")
    private Integer diaAniversarioPoupanca;
    
    protected Conta() {
        
    }
    
    public static Conta criarContaCorrente(ContaCorrenteInclusaoCommand command) {
        return new Conta(command);
    }
    
    private Conta (ContaCorrenteInclusaoCommand command) {
        this.codigoConta = this.gerarCodigoConta();
        this.cliente = cliente;
        this.codigoGerente = command.getCodigoGerente();
        this.codigoSituacaoConta = 10;
        this.dataAbertura = (Date) DateUtil.getDateNow();
        this.tipoConta = 1;
        this.valorSaldo = 0.00;
    }
            
    public static Conta criarContaPoupanca(ContaPoupancaInclusaoCommand command) {
        return new Conta(command);
    }
        
    private Conta (ContaPoupancaInclusaoCommand command) {
        this.codigoConta = this.gerarCodigoConta();
        this.cliente = cliente;
        this.codigoGerente = command.getCodigoGerente();
        this.codigoSituacaoConta = 10;
        this.dataAbertura = (Date) DateUtil.getDateNow();
        this.valorSaldo = 0.00;
        this.tipoConta = 2;
        this.diaAniversarioPoupanca = command.getDiaAniversarioPoupanca();
    
    }
    
    public static Conta criarContaSalario(ContaSalarioInclusaoCommand command) {
        return new Conta(command);
    }
    
    private Conta (ContaSalarioInclusaoCommand command) {
        this.codigoConta = this.gerarCodigoConta();
        this.cliente = cliente;
        this.codigoGerente = command.getCodigoGerente();
        this.codigoSituacaoConta = 10;
        this.dataAbertura = (Date) DateUtil.getDateNow();
        this.valorSaldo = 0.00;
        this.tipoConta = 3;
        this.numeroCnpjContratoSalario = command.getNumeroCnpjContratoSalario();
        
    }

    public static Conta editarContaCorrente(ContaCorrenteEdicaoCommand command) {
        Conta conta = new Conta();
        
        if (command!= null) {
            conta.editarConta(command);
            return conta;
        }
        return null;
    }
    
    public static Conta editarContaPoupanca(ContaPoupancaEdicaoCommand command) {
        Conta conta = new Conta();
        
        if (command!= null) {
            conta.editarConta(command);
            return conta;
        }
        return null;
    }
    
    public static Conta editarContaSalario(ContaSalarioEdicaoCommand command) {
        Conta conta = new Conta();
        
        if (command!= null) {
            conta.editarConta(command);
            return conta;
        }
        return null;
    } 
    
    private void editarConta(ContaCorrenteEdicaoCommand command) {
        this.codigoGerente = command.getCodigoGerente();
        this.codigoSituacaoConta = command.getCodigoSituacaoConta();
    }

    private void editarConta(ContaPoupancaEdicaoCommand command) {
        this.codigoGerente = command.getCodigoGerente();
        this.codigoSituacaoConta = command.getCodigoSituacaoConta();
        this.diaAniversarioPoupanca = command.getDiaAniversarioPoupanca();
    }
    
    private void editarConta(ContaSalarioEdicaoCommand command) {
        this.codigoGerente = command.getCodigoGerente();
        this.codigoSituacaoConta = command.getCodigoSituacaoConta();
        this.numeroCnpjContratoSalario = command.getNumeroCnpjContratoSalario();
    }
    
    private String gerarCodigoConta() {
        Random random = new Random();
        return Integer.toString(random.nextInt(1000));
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
