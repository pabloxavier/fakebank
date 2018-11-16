package br.com.fakebank.domain.commands;

import br.com.fakebank.domain.validators.CommandValidator;

public class ClientePessoaJuridicaEdicaoCommand {

    private boolean isAtivo;
    private String endereco;
    
    //TODO, quando tiver comando de Pessoa, usar aqui

    public ClientePessoaJuridicaEdicaoCommand() {
        
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
        
    	CommandValidator<ClientePessoaJuridicaEdicaoCommand> validator =
        		new CommandValidator<ClientePessoaJuridicaEdicaoCommand>();
        
        validator.validate(this);
    }

}
