package br.com.fakebank.domain.commands;

public class MovimentacaoSaqueCommand {

    private double valor;

    private String conta;

    public MovimentacaoSaqueCommand() {

    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }
}
