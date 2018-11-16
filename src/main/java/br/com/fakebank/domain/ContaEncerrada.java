package br.com.fakebank.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.fakebank.domain.commands.ContaEncerradaCommand;

@Entity
@Table(name = "conta_encerrada", schema = "dbo")
public class ContaEncerrada implements Serializable{

    @Id
    @Column(name = "cd_conta")
    private String conta;
    
    @Column(name = "dt_encerramento")
    private LocalDate dataEncerramento;
    
    @Column(name = "cd_motivo_encerramento")
    private Integer motivo;
    
    @Column(name = "cd_cliente_solicitante")
    private Integer clienteSolicitante;
    
    @Column(name = "ds_observacoes")
    private String observacoes;
    
    protected ContaEncerrada() {
    	
    }

    private ContaEncerrada(ContaEncerradaCommand comando, String conta){
        this.motivo = comando.getMotivo();
        this.observacoes = comando.getObservacoes();
        this.clienteSolicitante = comando.getClienteSolicitante();
        this.conta = conta;
        this.dataEncerramento = LocalDate.now();
    }
    
    public static ContaEncerrada criar(ContaEncerradaCommand comando, String conta) {
    	return new ContaEncerrada(comando, conta);
    }
    
    public String getConta() {
        return conta;
    }

    public LocalDate getDataEncerramento() {
        return dataEncerramento;
    }

    public Integer getMotivo() {
        return motivo;
    }

    public Integer getClienteSolicitante() {
        return clienteSolicitante;
    }

    public String getObservacoes() {
        return observacoes;
    }

}
