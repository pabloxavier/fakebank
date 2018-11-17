package br.com.fakebank.representations;

import java.time.LocalDate;

import br.com.fakebank.domain.Conta;
import br.com.fakebank.domain.Gerente;

public class ContaCorrenteRepresentation {

    private String codigoConta;
    private String nomeCliente;
    private LocalDate dataAbertura;
    private Gerente gerente;
    private String situacaoConta;
    private Double valorSaldo;

    public static ContaCorrenteRepresentation  from(Conta conta) {
        ContaCorrenteRepresentation contaRepresentation = new ContaCorrenteRepresentation();
        contaRepresentation.setCodigoConta(conta.getCodigoConta());
        contaRepresentation.setCodigoGerente(conta.getGerente());
        contaRepresentation.setCodigoSituacaoConta(conta.getSituacaoConta().getDescricao());
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

    public String getCodigoSituacaoConta() {
        return situacaoConta;
    }

    public void setCodigoSituacaoConta(String situacaoConta) {
        this.situacaoConta = situacaoConta;
    }

    public Double getValorSaldo() {
        return valorSaldo;
    }

    public void setValorSaldo(Double valorSaldo) {
        this.valorSaldo = valorSaldo;
    }
}
