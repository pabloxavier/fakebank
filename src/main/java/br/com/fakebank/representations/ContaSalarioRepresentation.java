package br.com.fakebank.representations;

import br.com.fakebank.domain.Conta;

import java.util.Date;

public class ContaSalarioRepresentation {

    private String codigoConta;
    private String nomeCliente;
    private Date dataAbertura;
    private Integer codigoGerente;
    private Integer codigoSituacaoConta;
    private Double valorSaldo;
    private String numeroCnpjContratoSalario;

    public static ContaSalarioRepresentation from(Conta conta) {
        ContaSalarioRepresentation contaRepresentation = new ContaSalarioRepresentation();
        contaRepresentation.setCodigoConta(conta.getCodigoConta());
        contaRepresentation.setCodigoGerente(conta.getCodigoGerente());
        contaRepresentation.setCodigoSituacaoConta(conta.getCodigoSituacaoConta());
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

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Integer getCodigoGerente() {
        return codigoGerente;
    }

    public void setCodigoGerente(Integer codigoGerente) {
        this.codigoGerente = codigoGerente;
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

    public String getNumeroCnpjContratoSalario() {
        return numeroCnpjContratoSalario;
    }

    public void setNumeroCnpjContratoSalario(String numeroCnpjContratoSalario) {
        this.numeroCnpjContratoSalario = numeroCnpjContratoSalario;
    }
}
