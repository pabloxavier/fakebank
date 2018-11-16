package br.com.fakebank.representations;

import java.time.LocalDate;

import br.com.fakebank.domain.Conta;
import br.com.fakebank.domain.Gerente;

public class ContaPoupancaRepresentation {

    private String codigoConta;
    private String nomeCliente;
    private LocalDate dataAbertura;
    private Gerente gerente;
    private String situacaoConta;
    private Double valorSaldo;
    private Integer diaAniversarioPoupanca;

    public static ContaPoupancaRepresentation from(Conta conta) {
        ContaPoupancaRepresentation contaRepresentation = new ContaPoupancaRepresentation();
        contaRepresentation.setCodigoConta(conta.getCodigoConta());
        contaRepresentation.setGerente(conta.getGerente());
        contaRepresentation.setSituacaoConta(conta.getSituacaoConta().getDescricao());
        contaRepresentation.setDataAbertura(conta.getDataAbertura());
        contaRepresentation.setDiaAniversarioPoupanca(conta.getDiaAniversarioPoupanca());
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

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public String getCodigoSituacaoConta() {
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

    public Integer getDiaAniversarioPoupanca() {
        return diaAniversarioPoupanca;
    }

    public void setDiaAniversarioPoupanca(Integer diaAniversarioPoupanca) {
        this.diaAniversarioPoupanca = diaAniversarioPoupanca;
    }
}
