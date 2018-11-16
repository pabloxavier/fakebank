package br.com.fakebank.representations;

import java.time.LocalDate;

import br.com.fakebank.domain.Conta;
import br.com.fakebank.domain.Gerente;

public class ContaCorrenteRepresentation {

    private String codigoConta;
    private String nomeCliente;
    private LocalDate dataAbertura;
    private Gerente gerente;
    private Integer codigoSituacaoConta;
    private Double valorSaldo;

    public static ContaCorrenteRepresentation  from(Conta conta) {
        ContaCorrenteRepresentation contaRepresentation = new ContaCorrenteRepresentation();
        contaRepresentation.setCodigoConta(conta.getCodigoConta());
        contaRepresentation.setCodigoGerente(conta.getGerente());
        contaRepresentation.setCodigoSituacaoConta(conta.getCodigoSituacaoConta());
        contaRepresentation.setDataAbertura(conta.getDataAbertura());
        contaRepresentation.setNomeCliente(conta.getCliente().getPessoa().getNome());
        contaRepresentation.setValorSaldo(conta.getValorSaldo());
        return contaRepresentation;
    }

    public String getCodigoConta() {
        return codigoConta;
    }

    public void setCodigoConta(String codigoConta) {
        this.codigoConta = codigoConta;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Gerente getCodigoGerente() {
        return gerente;
    }

    public void setCodigoGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public Integer getCodigoSituacaoConta() {
        return codigoSituacaoConta;
    }

    public void setCodigoSituacaoConta(Integer codigoSituacaoConta) {
        this.codigoSituacaoConta = codigoSituacaoConta;
    }

    public Double getValorSaldo() {
        return valorSaldo;
    }

    public void setValorSaldo(Double valorSaldo) {
        this.valorSaldo = valorSaldo;
    }
}
