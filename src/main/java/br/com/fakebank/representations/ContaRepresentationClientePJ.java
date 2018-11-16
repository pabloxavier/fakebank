package br.com.fakebank.representations;

import java.time.LocalDate;
import java.util.stream.Collectors;

import br.com.fakebank.domain.Conta;
import br.com.fakebank.domain.Gerente;
import br.com.fakebank.util.ListaPaginada;
import org.springframework.data.domain.Page;

public class ContaRepresentationClientePJ {
	
	private String codigoConta;
	private Integer codigoSituacaoConta;
	private Integer tipoConta;
	private Double valorSaldo;
	private LocalDate dataAbertura;
	private Gerente gerente;
	
	public static ContaRepresentationClientePJ from (Conta conta) {
		ContaRepresentationClientePJ contaRepresentationClientePJ = new ContaRepresentationClientePJ();
		contaRepresentationClientePJ.setCodigoConta(conta.getCodigoConta());
		contaRepresentationClientePJ.setCodigoSituacaoConta(conta.getCodigoSituacaoConta());
		contaRepresentationClientePJ.setTipoConta(conta.getTipoConta());
		contaRepresentationClientePJ.setValorSaldo(conta.getValorSaldo());
		contaRepresentationClientePJ.setDataAbertura(conta.getDataAbertura());
		contaRepresentationClientePJ.setGerente(conta.getGerente());
		return contaRepresentationClientePJ;
	}
	
	public static ListaPaginada<ContaRepresentationClientePJ> from (Page<Conta> contas){
		ListaPaginada<ContaRepresentationClientePJ> lista = new ListaPaginada<>();
		
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
	public Integer getCodigoSituacaoConta() {
		return codigoSituacaoConta;
	}
	public void setCodigoSituacaoConta(Integer codigoSituacaoConta) {
		this.codigoSituacaoConta = codigoSituacaoConta;
	}
	public Integer getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(Integer tipoConta) {
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
