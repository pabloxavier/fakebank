package br.com.fakebank.domain.commands;


import java.time.LocalDate;

import br.com.fakebank.common.validations.CommandValidator;

public class ClientePessoaFisicaInclusaoCommand {

    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
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
