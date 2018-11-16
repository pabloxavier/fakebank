package br.com.fakebank.representations;

import java.time.LocalDate;

import br.com.fakebank.domain.Conta;
import br.com.fakebank.domain.Gerente;

public class ContaRepresentation {

    private String codigoConta;
    private String nomeCliente;
    private LocalDate dataAbertura;
    private Integer tipoConta;
    private Gerente gerente;
    private Integer codigoSituacaoConta;
    private Double valorSaldo;
    private String numeroCnpjContratoSalario;
    private Integer diaAniversarioPoupanca;


    public static ContaRepresentation from(Conta conta) {
        ContaRepresentation contaRepresentation = new ContaRepresentation();
        contaRepresentation.setCodigoConta(conta.getCodigoConta());
        contaRepresentation.setGerente(conta.getGerente());
        contaRepresentation.setCodigoSituacaoConta(conta.getCodigoSituacaoConta());
        contaRepresentation.setDataAbertura(conta.getDataAbertura());
        contaRepresentation.setDiaAniversarioPoupanca(conta.getDiaAniversarioPoupanca());
        contaRepresentation.setNomeCliente(conta.getCliente().getPessoa().getNome());
        contaRepresentation.setNumeroCnpjContratoSalario(conta.getNumeroCnpjContratoSalario());
        contaRepresentation.setTipoConta(conta.getTipoConta());
        contaRepresentation.setValorSaldo(conta.getValorSaldo());
        return contaRepresentation;
    }

    public String getCodigoConta() {
        return codigoConta;
    }

    public void setCodigoConta(String codigoConta) {
        this.codigoConta = codigoConta;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Integer getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(Integer tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
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

    public String getNumeroCnpjContratoSalario() {
        return numeroCnpjContratoSalario;
    }

    public void setNumeroCnpjContratoSalario(String numeroCnpjContratoSalario) {
        this.numeroCnpjContratoSalario = numeroCnpjContratoSalario;
    }

    public Integer getDiaAniversarioPoupanca() {
        return diaAniversarioPoupanca;
    }

    public void setDiaAniversarioPoupanca(Integer diaAniversarioPoupanca) {
        this.diaAniversarioPoupanca = diaAniversarioPoupanca;
    }
}
