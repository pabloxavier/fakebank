package br.com.fakebank.domain;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.fakebank.domain.commands.GerenteEdicaoCommand;
import br.com.fakebank.domain.commands.GerenteInclusaoCommand;
import br.com.fakebank.domain.converters.StatusBooleanConverter;

@Entity
@Table(name = "GERENTE", schema = "dbo")
public class Gerente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_GERENTE")
    private Integer codGerente;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CD_PESSOA")
    private Pessoa pessoa;
    
    @Column(name = "IS_ATIVO")
    @Convert(converter=StatusBooleanConverter.class)
    private boolean isAtivo;
    
    @ManyToMany
    @JoinTable(name = "GERENTE_AGENCIA",
               schema = "DBO",
               joinColumns = {@JoinColumn(name = "CD_GERENTE")},
               inverseJoinColumns = {@JoinColumn(name = "CD_AGENCIA")})
    private List<Agencia> agenciasGerenciadas;
    
    public Integer getCodGerente() {
        return codGerente;
    }
    public Pessoa getPessoa() {
        return pessoa;
    }
    public boolean isAtivo() {
        return isAtivo;
    }

    protected Gerente(){
        
    }
    
    private Gerente (GerenteInclusaoCommand comando){
        this.isAtivo  = comando.isAtivo();
        this.pessoa   = comando.getPessoa();
    }
    
    public static Gerente criar(GerenteInclusaoCommand comando){
        return new Gerente(comando);
    }
    
    public void editar(GerenteEdicaoCommand comando){
        this.isAtivo = comando.isAtivo();
    }

}