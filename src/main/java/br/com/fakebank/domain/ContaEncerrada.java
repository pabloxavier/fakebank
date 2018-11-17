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
import br.com.fakebank.repository.ClienteRepository;

@Entity
@Table(name = "conta_encerrada", schema = "dbo")
public class ContaEncerrada implements Serializable{

    /**
     * Identificador de serialização da versão da classe
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_conta")
    private String conta;
    
    @Column(name = "dt_encerramento")
    private LocalDate dataEncerramento;
    
    @Column(name = "cd_motivo_encerramento")
    private Integer motivo;
    
    @ManyToOne
    @JoinColumn(name = "cd_cliente_solicitante")
    private Cliente clienteSolicitante;
    
    @Column(name = "ds_observacoes")
    private String observacoes;
    
    protected ContaEncerrada() {
    	
    }

    private ContaEncerrada(ContaEncerradaCommand comando, String conta, Cliente cliente){
        this.motivo = comando.getMotivo();
        this.observacoes = comando.getObservacoes();
        this.clienteSolicitante = cliente;
        this.conta = conta;
        this.dataEncerramento = LocalDate.now();
    }
    
    public static ContaEncerrada criar(ContaEncerradaCommand comando, String conta, Cliente cliente) {
    	return new ContaEncerrada(comando, conta, cliente);
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

    public Cliente getClienteSolicitante() {
        return clienteSolicitante;
    }

    public String getObservacoes() {
        return observacoes;
    }

}
