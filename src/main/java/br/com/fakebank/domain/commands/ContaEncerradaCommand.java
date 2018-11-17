package br.com.fakebank.domain.commands;

public class ContaEncerradaCommand {

    private Integer motivo;
    private String observacoes;
    private Integer clienteSolicitante;
    
    public ContaEncerradaCommand() {
        
    }

    public Integer getMotivo() {
        return motivo;
    }

    public void setMotivo(Integer motivo) {
        this.motivo = motivo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getClienteSolicitante() {
        return clienteSolicitante;
    }

    public void setClienteSolicitante(Integer clienteSolicitante) {
        this.clienteSolicitante = clienteSolicitante;
    }
    
}
