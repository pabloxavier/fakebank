package br.com.fakebank.domain.commands;


import java.util.Date;

import br.com.fakebank.domain.validators.CommandValidator;

public class ClientePessoaFisicaInclusaoCommand {

    private String cpf;
    private String nome;
    private Date dataNascimento;
    private String enderecoCompleto;

    public ClientePessoaFisicaInclusaoCommand() {

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }

    public void validate() {
        
    	CommandValidator<ClientePessoaFisicaInclusaoCommand> validator =
        		new CommandValidator<ClientePessoaFisicaInclusaoCommand>();
        
        validator.validate(this);
    }
    
}
