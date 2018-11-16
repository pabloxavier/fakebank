package br.com.fakebank.domain.commands;

import br.com.fakebank.domain.validators.CommandValidator;

public class ClientePessoaFisicaEdicaoCommand {

    private boolean isAtivo;
    private String endereco;
    
    //TODO, quando tiver comando de Pessoa, usar aqui

    public ClientePessoaFisicaEdicaoCommand() {
        
    }
    
    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public void validate() {
        
    	CommandValidator<ClientePessoaFisicaEdicaoCommand> validator =
        		new CommandValidator<ClientePessoaFisicaEdicaoCommand>();
        
        validator.validate(this);
    }

}
