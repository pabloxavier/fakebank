package br.com.fakebank.representations;


import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.fakebank.common.util.ListaPaginada;
import br.com.fakebank.domain.Conta;
import br.com.fakebank.domain.Gerente;


public class ContaRepresentationClientePF {

	 private String codigoConta;
	   private String situacaoConta;
	   private String tipoConta;
	   private Double valorSaldo;
	   private LocalDate dataAbertura;
	   private Gerente gerente;

	   public static ContaRepresentationClientePF from(Conta conta) {
	       ContaRepresentationClientePF contaRepresentationClientePf = new ContaRepresentationClientePF();
	       contaRepresentationClientePf.setCodigoConta(conta.getCodigoConta());
	       contaRepresentationClientePf.setSituacaoConta(conta.getSituacaoConta().getDescricao());
	       contaRepresentationClientePf.setTipoConta(conta.getTipoConta().getDescricao());
	       contaRepresentationClientePf.setValorSaldo(conta.getValorSaldo());
	       contaRepresentationClientePf.setDataAbertura(conta.getDataAbertura());
	       contaRepresentationClientePf.setGerente(conta.getGerente());
	       return contaRepresentationClientePf;
	   }

	   public static ListaPaginada<ContaRepresentationClientePF> from(Page<Conta> contas) {
	        ListaPaginada<ContaRepresentationClientePF> lista =
	                new ListaPaginada<ContaRepresentationClientePF>();

	        lista.setContent(contas
	                			.stream()
	                			.map(item -> from(item))
	                			.collect(Collectors.toList()));
	        
	        lista.setTotalPages(contas.getTotalPages());
	        lista.setPageNumber(contas.getPageable().getPageNumber());
	        lista.setPageSize(contas.getPageable().getPageSize());
	        return lista;
	   }
	   
	   
	    public String getCodigoConta() {
	        return codigoConta;
	    }

	    public void setCodigoConta(String codigoConta) {
	        this.codigoConta = codigoConta;
	    }

	    public String getSituacaoConta() {
	        return situacaoConta;
	    }

	    public void setSituacaoConta(String situacaoConta) {
	        this.situacaoConta = situacaoConta;
	    }

	    public String getTipoConta() {
	        return tipoConta;
	    }

	    public void setTipoConta(String tipoConta) {
	        this.tipoConta = tipoConta;
	    }

	    public Double getValorSaldo() {
	        return valorSaldo;
	    }

	    public void setValorSaldo(Double valorSaldo) {
	        this.valorSaldo = valorSaldo;
	    }

	    public LocalDate getDataAbertura() {
	        return dataAbertura;
	    }

	    public void setDataAbertura(LocalDate dataAbertura) {
	        this.dataAbertura = dataAbertura;
	    }

	    public Gerente getGerente() {
	        return gerente;
	    }

	    public void setGerente(Gerente gerente) {
	        this.gerente = gerente;
	    }

	}	
	
