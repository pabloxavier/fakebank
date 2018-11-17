package br.com.fakebank.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.fakebank.domain.commands.ClientePessoaFisicaEdicaoCommand;
import br.com.fakebank.domain.commands.ClientePessoaFisicaInclusaoCommand;
import br.com.fakebank.domain.commands.ClientePessoaJuridicaEdicaoCommand;
import br.com.fakebank.domain.commands.ClientePessoaJuridicaInclusaoCommand;

@Entity
@Table(name = "cliente", schema = "dbo")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_cliente")
    private Integer codigo;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_pessoa")
    private Pessoa pessoa;
    
    @Column(name = "is_ativo")
    private boolean isAtivo;
    
    @Column(name = "ds_endereco_completo")
    private String endereco;

    protected Cliente(){
        
    }
    
    //Criar e Editar PF
    private Cliente(ClientePessoaFisicaInclusaoCommand comando) {
        this.pessoa = new Pessoa();
        this.pessoa.setNumeroDocumento(comando.getCpf());
        this.pessoa.setNome(comando.getNome());
        this.pessoa.setDataNascimento(comando.getDataNascimento());
        this.pessoa.setTipoPessoa(TipoPessoa.FISICA);
        this.endereco = comando.getEnderecoCompleto();
        this.isAtivo = Boolean.TRUE;
    }
    
    public static Cliente criarClientePessoaFisica(ClientePessoaFisicaInclusaoCommand comando) {
       
    	comando.validate();
    	
    	return new Cliente(comando);
    }
    
    public void editar(ClientePessoaFisicaEdicaoCommand comando){
    	
    	comando.validate();
    	
    	this.endereco = comando.getEndereco();
    	this.isAtivo = comando.isAtivo();
	}

    //Criar e Editar PJ
    private Cliente(ClientePessoaJuridicaInclusaoCommand comando) {
        this.pessoa = new Pessoa();
        this.pessoa.setNumeroDocumento(comando.getCnpj());
        this.pessoa.setNome(comando.getNome());
        this.pessoa.setDataAbertura(comando.getDataAbertura());
        this.pessoa.setTipoPessoa(TipoPessoa.JURIDICA);
        this.endereco = comando.getEnderecoCompleto();
        this.isAtivo = Boolean.TRUE;
    }
    
    public static Cliente criarClientePessoaJuridica(ClientePessoaJuridicaInclusaoCommand comando) {
        
    	comando.validate();
    	
    	return new Cliente(comando);
    }
    
    public void editar(ClientePessoaJuridicaEdicaoCommand comando){
    	
    	comando.validate();
    	
    	this.endereco = comando.getEndereco();
    	this.isAtivo = comando.isAtivo();
	}
    
        
    // gets
    public Integer getCodigo() {
        return codigo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public String getEndereco() {
        return endereco;
    }

}
