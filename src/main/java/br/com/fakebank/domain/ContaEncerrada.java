package br.com.fakebank.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "conta_encerrada", schema = "dbo")
public class ContaEncerrada implements Serializable{

    @Id
    @OneToOne
    @JoinColumn(name = "cd_conta")
    private Conta conta;
    
    @Column(name = "dt_encerramento")
    private Date dataEncerramento;
    
    @ManyToOne
    @JoinColumn(name = "cd_motivo_encerramento")
    private MotivoEncerramento motivo;
    
    @ManyToOne
    @JoinColumn(name = "cd_cliente_solicitante")
    private Cliente clienteSolicitante;
    
    @Column(name = "ds_observacoes")
    private String observacoes;
    
    public ContaEncerrada() {
        
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public MotivoEncerramento getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoEncerramento motivo) {
        this.motivo = motivo;
    }

    public Cliente getClienteSolicitante() {
        return clienteSolicitante;
    }

    public void setClienteSolicitante(Cliente clienteSolicitante) {
        this.clienteSolicitante = clienteSolicitante;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
}
