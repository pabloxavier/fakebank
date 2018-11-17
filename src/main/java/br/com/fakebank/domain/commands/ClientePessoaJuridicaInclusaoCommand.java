package br.com.fakebank.domain.commands;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.fakebank.common.validations.CommandValidator;
import br.com.fakebank.common.validations.FieldName;
import br.com.fakebank.domain.validators.CnpjValid;

public class ClientePessoaJuridicaInclusaoCommand {

    @NotNull
    @NotBlank
    @CnpjValid
    @FieldName("CNPJ")
    private String cnpj;
    
    @NotNull(message = "{clientepj.nome.nao.nulo}")
    @NotBlank(message = "{clientepj.nome.nao.vazio}")
    @FieldName("Nome do Cliente")
    private String nome;
    
    private LocalDate dataAbertura;
    private String enderecoCompleto;
    
    public ClientePessoaJuridicaInclusaoCommand() {
        
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }
    
    public void validate() {
        
    	CommandValidator<ClientePessoaJuridicaInclusaoCommand> validator =
        		new CommandValidator<ClientePessoaJuridicaInclusaoCommand>();
        
        validator.validate(this);
    }

}
