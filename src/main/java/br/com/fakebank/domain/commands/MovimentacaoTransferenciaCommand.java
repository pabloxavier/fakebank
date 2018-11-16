package br.com.fakebank.domain.commands;

public class MovimentacaoTransferenciaCommand {

    private double valor;

    private String contaOrigem;

    private String contaDestino;

    public MovimentacaoTransferenciaCommand() {
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

}