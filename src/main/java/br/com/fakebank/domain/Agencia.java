package br.com.fakebank.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.fakebank.domain.commands.AgenciaEdicaoCommand;
import br.com.fakebank.domain.commands.AgenciaInclusaoCommand;

@Entity
@Table(name = "agencia", schema = "dbo")
public class Agencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_agencia")
    private Integer codigo;
    
    @Column(name = "nr_agencia")
    private Integer numero;
    
    @Column(name = "nm_agencia")
    private String nome;
    
    @Column(name = "nr_cnpj")
    private String cnpj;
    
    @ManyToMany
    @JoinTable(name = "GERENTE_AGENCIA",
               schema = "DBO",
               joinColumns = {@JoinColumn(name = "CD_AGENCIA")},
               inverseJoinColumns = {@JoinColumn(name = "CD_GERENTE")})
    private List<Gerente> gerentes;
    
    protected Agencia(){
        
    }
    
    private Agencia(AgenciaInclusaoCommand comando){
        this.numero = comando.getNumero();
        this.nome = comando.getNome();
        this.cnpj = comando.getCnpj();
    }
    
    public static Agencia criar(AgenciaInclusaoCommand comando){
        
        comando.validate();
        
        return new Agencia(comando);
    }
    
    public void editar(AgenciaEdicaoCommand comando){
        
        //validacao()
        
        this.numero = comando.getNumero();
        this.nome = comando.getNome();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }
}
