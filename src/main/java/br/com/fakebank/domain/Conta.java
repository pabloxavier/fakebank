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
import org.springframework.data.jpa.domain.Specification;

import br.com.fakebank.domain.commands.ContaCorrenteEdicaoCommand;
import br.com.fakebank.domain.commands.ContaCorrenteInclusaoCommand;
import br.com.fakebank.domain.commands.ContaEncerradaCommand;
import br.com.fakebank.domain.commands.ContaPoupancaEdicaoCommand;
import br.com.fakebank.domain.commands.ContaPoupancaInclusaoCommand;
import br.com.fakebank.domain.commands.ContaSalarioEdicaoCommand;
import br.com.fakebank.domain.commands.ContaSalarioInclusaoCommand;
import br.com.fakebank.domain.specifications.DominioSpecifications;
import br.com.fakebank.exceptions.NotFoundException;
import br.com.fakebank.repository.DominioRepository;
import br.com.fakebank.repository.GerenteRepository;

@Entity
@Table(name = "CONTA", schema = "dbo")
public class Conta {

	public static final Double SALDO_INICIAL = 0.00;
	private static final String TIPO_CONTA = "tipo_conta";
	private static final String SITUACAO_CONTA = "sit_conta";
	private static final Integer VALOR_DOMINIO_CONTA_SALARIO = 1;
	private static final Integer VALOR_DOMINIO_CONTA_CORRENTE = 2;
	private static final Integer VALOR_DOMINIO_CONTA_POUPANCA = 3;
	private static final Integer VALOR_DOMINIO_SITUACAO_CONTA_LIVRE = 1;

	//TODO Itens não utilizados
	//private static final Integer VALOR_DOMINIO_SITUACAO_CONTA_FECHADA = 2;
	//private static final Integer VALOR_DOMINIO_SITUACAO_CONTA_PREJUIZO = 3;
	//private static final Integer VALOR_DOMINIO_SITUACAO_CONTA_CARTORIO = 4;
	
	@Id
	@Column(name = "CD_CONTA")
	private String codigoConta;
	
	@ManyToOne()
	@JoinColumn(name = "CD_CLIENTE_PRINCIPAL")
	private Cliente cliente;	
	
	@Column(name = "DT_ABERTURA")
	private LocalDate dataAbertura;
	
	@ManyToOne()
	@JoinColumn(name = "TP_CONTA")
	private TipoConta tipoConta;
  
	@ManyToOne()
	@JoinColumn(name = "CD_GERENTE")
	private Gerente gerente;
	
	@ManyToOne()
	@JoinColumn(name = "CD_SITUACAO_CONTA")
	private SituacaoConta situacaoConta;
	
	@Column(name = "VL_SALDO")
	private Double valorSaldo;
	
	@Column(name = "NR_CNPJ_CONTRATO_SALARIO")
	private String numeroCnpjContratoSalario;
	
	@Column(name ="DD_ANIVERSARIO_POUPANCA")
	private Integer diaAniversarioPoupanca;
	
	@Transient
	@Autowired
	private GerenteRepository gerenteRepository;
	
	@Transient
	@Autowired
	private DominioRepository dominioRepository;
	
	protected Conta() {
		
	}
	
	public static Conta criarContaCorrente(Cliente cliente, ContaCorrenteInclusaoCommand command) {
		
		//command.validate();
		
		return new Conta(cliente, command);
	}
	
	private Conta (Cliente cliente, ContaCorrenteInclusaoCommand command) {
		this.codigoConta = this.gerarCodigoConta();
		this.cliente = cliente;		
		this.gerente = getGerenteById(command.getCodigoGerente());	
		this.situacaoConta = getSituacaoContaByValorTipo(VALOR_DOMINIO_SITUACAO_CONTA_LIVRE, SITUACAO_CONTA);
		this.dataAbertura = LocalDate.now();
		this.tipoConta = getTipoContaByValorTipo(VALOR_DOMINIO_CONTA_CORRENTE, TIPO_CONTA);
		this.valorSaldo = SALDO_INICIAL;
	}
			
	public static Conta criarContaPoupanca(Cliente cliente, ContaPoupancaInclusaoCommand command) {
		
		command.validate();
		
		return new Conta(cliente, command);
	}
		
	private Conta (Cliente cliente, ContaPoupancaInclusaoCommand command) {
		this.codigoConta = this.gerarCodigoConta();
		this.cliente = cliente;
		this.gerente = getGerenteById(command.getCodigoGerente());	
		this.situacaoConta = getSituacaoContaByValorTipo(VALOR_DOMINIO_SITUACAO_CONTA_LIVRE, SITUACAO_CONTA);
		this.dataAbertura = LocalDate.now();
		this.valorSaldo = SALDO_INICIAL;		
		this.tipoConta = getTipoContaByValorTipo(VALOR_DOMINIO_CONTA_POUPANCA, TIPO_CONTA);
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
		this.situacaoConta = getSituacaoContaByValorTipo(VALOR_DOMINIO_SITUACAO_CONTA_LIVRE, SITUACAO_CONTA);	
		this.dataAbertura = LocalDate.now();
		this.valorSaldo = SALDO_INICIAL;		
		this.tipoConta = getTipoContaByValorTipo(VALOR_DOMINIO_CONTA_SALARIO, TIPO_CONTA);
		this.numeroCnpjContratoSalario = command.getNumeroCnpjContratoSalario();
		
	}
	
	public void editarContaSalario(ContaSalarioEdicaoCommand command) {
		
		command.validate();
		
		this.gerente = getGerenteById(command.getCodigoGerente());			
		Dominio dominio = getDominioByCodigo(command.getCodigoSituacaoConta());
		this.situacaoConta = getSituacaoContaByDominio(dominio);							
		this.numeroCnpjContratoSalario = command.getNumeroCnpjContratoSalario();		
		
	} 
	
	public void editarContaCorrente(ContaCorrenteEdicaoCommand command) {
		
		command.validate();
		this.gerente = getGerenteById(command.getCodigoGerente());		
		Dominio dominio = getDominioByCodigo(command.getCodigoSituacaoConta());
		this.situacaoConta = getSituacaoContaByDominio(dominio);						
	}	

	public void editarContaPoupanca(ContaPoupancaEdicaoCommand command) {
		
		command.validate();
		
		this.gerente = getGerenteById(command.getCodigoGerente());		
		Dominio dominio = getDominioByCodigo(command.getCodigoSituacaoConta());
		this.situacaoConta = getSituacaoContaByDominio(dominio);						
		this.diaAniversarioPoupanca = command.getDiaAniversarioPoupanca();		
	}	
	
	public ContaEncerrada encerrarConta(ContaEncerradaCommand command) {
		
		ContaEncerrada encerramento = ContaEncerrada.criar(command, this.codigoConta);
		return encerramento;

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

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public SituacaoConta getSituacaoConta() {
        return situacaoConta;
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
    	return gerenteRepository.findById(codigoGerente).orElse(null);
    }

    public SituacaoConta getSituacaoContaByValorTipo(Integer valor, String tipo) {
    	Specification<Dominio> criteria = Specification.where(DominioSpecifications.dominioPorValor(valor))
				.and(DominioSpecifications.dominioPorTipo(tipo));
    	Dominio dominio = dominioRepository.findOne(criteria).orElse(null);    	

    	if (dominio != null) {
    		return buildSituacaoContaAPartirDominio(dominio);
    	}
    	throw new NotFoundException("Situação de Conta não encontrada.");
    	
    }
    
    public SituacaoConta getSituacaoContaByDominio(Dominio dominioParam) {
    	Dominio dominio = dominioRepository.findById(dominioParam.getCodigo()).orElse(null);    	

    	if (dominio != null) {
    		return buildSituacaoContaAPartirDominio(dominio);
    	}
    	throw new NotFoundException("Situação de Conta não encontrada.");
    	
    }
    
    
    public TipoConta getTipoContaByValorTipo(Integer valor, String tipo) {
    	Specification<Dominio> criteria = Specification.where(DominioSpecifications.dominioPorValor(valor))
    										.and(DominioSpecifications.dominioPorTipo(tipo));
    	Dominio dominio = dominioRepository.findOne(criteria).orElse(null);    	
    	
    	if (dominio != null) {
    		return buildTipoContaAPartirDominio(dominio);
    	}
    	throw new NotFoundException("Tipo de Conta não encontrada.");
    }
    
    public TipoConta getTipoContaByDominio(Dominio dominioParam) {
   
    	Dominio dominio = dominioRepository.findById(dominioParam.getCodigo()).orElse(null);    	
    	
    	if (dominio != null) {
    		return buildTipoContaAPartirDominio(dominio);
    	}
    	throw new NotFoundException("Tipo de Conta não encontrada.");
    }
    
    public TipoConta buildTipoContaAPartirDominio(Dominio dominio) {
    	TipoConta  tipoConta = new TipoConta();
    	tipoConta.setCodigo(dominio.getCodigo());
    	tipoConta.setDescricao(dominio.getDescricao());
    	tipoConta.setTipo(dominio.getTipo());
    	tipoConta.setValor(dominio.getValor());
    	
    	return tipoConta;
    } 	 
 
    public SituacaoConta buildSituacaoContaAPartirDominio(Dominio dominio) {
    	SituacaoConta  situacaoConta = new SituacaoConta();
    	situacaoConta.setCodigo(dominio.getCodigo());
    	situacaoConta.setDescricao(dominio.getDescricao());
    	situacaoConta.setTipo(dominio.getTipo());
    	situacaoConta.setValor(dominio.getValor());
    	
    	return situacaoConta;
    }
    
    public Dominio getDominioByCodigo(Integer codigo) {
    	return dominioRepository.getOne(codigo);
    }
    
    
}
