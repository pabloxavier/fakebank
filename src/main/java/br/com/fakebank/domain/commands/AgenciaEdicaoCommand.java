package br.com.fakebank.domain.commands;

public class AgenciaEdicaoCommand {

    private Integer numero;
    private String nome;
    
    public AgenciaEdicaoCommand(){
        
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
