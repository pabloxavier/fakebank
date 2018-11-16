package br.com.fakebank.domain;



import java.time.LocalDate;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fakebank.customValidators.ContaForeignKeySituacaoConta;
import br.com.fakebank.domain.commands.ContaCorrenteEdicaoCommand;
import br.com.fakebank.domain.commands.ContaCorrenteInclusaoCommand;
import br.com.fakebank.domain.commands.ContaPoupancaEdicaoCommand;
import br.com.fakebank.domain.commands.ContaPoupancaInclusaoCommand;
import br.com.fakebank.domain.commands.ContaSalarioEdicaoCommand;
import br.com.fakebank.domain.commands.ContaSalarioInclusaoCommand;
import br.com.fakebank.service.GerenteService;

@Entity
@Table(name = "CONTA", schema = "dbo")
public class Conta {

	@Id
	@Column(name = "CD_CONTA")
	private String codigoConta;
	
	@ManyToOne()
	@JoinColumn(name = "CD_CLIENTE_PRINCIPAL")
	private Cliente cliente;	
	
	@Column(name = "DT_ABERTURA")
	private LocalDate dataAbertura;
	
	@Column(name = "TP_CONTA")
	private Integer tipoConta;
  
	@ManyToOne()
	@JoinColumn(name = "CD_GERENTE")
	private Gerente gerente;
	
	@Column(name = "CD_SITUACAO_CONTA")
	@ContaForeignKeySituacaoConta
	private Integer codigoSituacaoConta;
	
	@Column(name = "VL_SALDO")
	private Double valorSaldo;
	
	@Column(name = "NR_CNPJ_CONTRATO_SALARIO")
	private String numeroCnpjContratoSalario;
	
	@Column(name ="DD_ANIVERSARIO_POUPANCA")
	private Integer diaAniversarioPoupanca;
	
	
	@Autowired
	@Transient
	private GerenteService gerenteService;
	
	
	protected Conta() {
		
	}
	
	public static Conta criarContaCorrente(Cliente cliente, ContaCorrenteInclusaoCommand command) {
		
		command.validate();
		
		return new Conta(cliente, command);
	}
	
	private Conta (Cliente cliente, ContaCorrenteInclusaoCommand command) {
		this.codigoConta = this.gerarCodigoConta();
		this.cliente = cliente;		
		this.gerente = getGerenteById(command.getCodigoGerente());	
		this.codigoSituacaoConta = 4;
		this.dataAbertura = LocalDate.now();
		this.tipoConta = 1;
		this.valorSaldo = 0.00;
	}
			
	public static Conta criarContaPoupanca(Cliente cliente, ContaPoupancaInclusaoCommand command) {
		
		command.validate();
		
		return new Conta(cliente, command);
	}
		
	private Conta (Cliente cliente, ContaPoupancaInclusaoCommand command) {
		this.codigoConta = this.gerarCodigoConta();
		this.cliente = cliente;
		this.gerente = getGerenteById(command.getCodigoGerente());	
		this.codigoSituacaoConta = 4;	
		this.dataAbertura = LocalDate.now();
		this.valorSaldo = 0.00;		
		this.tipoConta = 3;
		this.diaAniversarioPoupanca = command.getDiaAniversarioPoupanca();
		
	}	
			
	public static Conta criarContaSalario(Cliente cliente, ContaSalarioInclusaoCommand command) {
		
		command.validate();
		
		return new Conta(cliente, command);
	}
	
	private Conta (Cliente cliente, ContaSalarioInclusaoCommand command) {
		this.codigoConta = this.gerarCodigoConta();
		this.cliente = cliente;
		this.gerente = getGerenteById(command.getCodigoGerente());	
		this.codigoSituacaoConta = 4;	
		this.dataAbertura = LocalDate.now();
		this.valorSaldo = 0.00;		
		this.tipoConta = 2;
		this.numeroCnpjContratoSalario = command.getNumeroCnpjContratoSalario();
		
	}
	
	public void editarContaSalario(ContaSalarioEdicaoCommand command) {
		
		command.validate();
		
		this.gerente = getGerenteById(command.getCodigoGerente());			
		this.codigoSituacaoConta = command.getCodigoSituacaoConta();							
		this.numeroCnpjContratoSalario = command.getNumeroCnpjContratoSalario();		
		
	} 
	
	public void editarContaCorrente(ContaCorrenteEdicaoCommand command) {
		
		command.validate();
		this.gerente = getGerenteById(command.getCodigoGerente());		
		this.codigoSituacaoConta = command.getCodigoSituacaoConta();						
	}	

	public void editarContaPoupanca(ContaPoupancaEdicaoCommand command) {
		
		command.validate();
		
		this.gerente = getGerenteById(command.getCodigoGerente());		
		this.codigoSituacaoConta = command.getCodigoSituacaoConta();							
		this.diaAniversarioPoupanca = command.getDiaAniversarioPoupanca();		
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

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public Integer getTipoConta() {
        return tipoConta;
    }

    public Gerente getGerente() {
        return gerente;
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
    
    public Gerente getGerenteById(Integer codigoGerente) {
    	return gerenteService.getGerenteById(codigoGerente);
    }
    

	@Override
	public String toString() {
		return "Conta [codigoConta=" + codigoConta + ", cliente=" + cliente + ", dataAbertura=" + dataAbertura
				+ ", tipoConta=" + tipoConta + ", gerente=" + gerente + ", codigoSituacaoConta=" + codigoSituacaoConta
				+ ", valorSaldo=" + valorSaldo + ", numeroCnpjContratoSalario=" + numeroCnpjContratoSalario
				+ ", diaAniversarioPoupanca=" + diaAniversarioPoupanca + "]";
	}

}
