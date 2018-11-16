package br.com.fakebank.representations;

import java.time.LocalDate;

import br.com.fakebank.domain.Conta;
import br.com.fakebank.domain.Gerente;

public class ContaSalarioRepresentation {

    private String codigoConta;
    private String nomeCliente;
    private LocalDate dataAbertura;
    private Gerente gerente;
    private String situacaoConta;
    private Double valorSaldo;
    private String numeroCnpjContratoSalario;

    public static ContaSalarioRepresentation from(Conta conta) {
        ContaSalarioRepresentation contaRepresentation = new ContaSalarioRepresentation();
        contaRepresentation.setCodigoConta(conta.getCodigoConta());
        contaRepresentation.setGerente(conta.getGerente());
        contaRepresentation.setSituacaoConta(conta.getSituacaoConta().getDescricao());
        contaRepresentation.setDataAbertura(conta.getDataAbertura());
        contaRepresentation.setNomeCliente(conta.getCliente().getPessoa().getNome());
        contaRepresentation.setNumeroCnpjContratoSalario(conta.getNumeroCnpjContratoSalario());
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

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public String getSituacaoConta() {
        return situacaoConta;
    }

    public void setSituacaoConta(String situacaoConta) {
        this.situacaoConta = situacaoConta;
    }

    public Double getValorSaldo() {
        return valorSaldo;
    }

    public void setValorSaldo(Double valorSaldo) {
        this.valorSaldo = valorSaldo;
    }

    public String getNumeroCnpjContratoSalario() {
        return numeroCnpjContratoSalario;
    }

    public void setNumeroCnpjContratoSalario(String numeroCnpjContratoSalario) {
        this.numeroCnpjContratoSalario = numeroCnpjContratoSalario;
    }
}
