package br.com.fakebank.domain.commands;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import br.com.fakebank.domain.validators.CommandValidator;
import br.com.fakebank.exceptions.FieldName;

public class AgenciaEdicaoCommand {

    @NotNull
    @Range(min = 1, max = 9999)
    @FieldName("Número")
	@ApiModelProperty(notes = "Número de identificação da agência.")
    private Integer numero;
    
    @NotNull(message = "{agencia.nome.nao.nulo}")
    @NotBlank(message = "{agencia.nome.nao.vazio}")
	@ApiModelProperty(notes = "Nome da agência.")
    private String nome;
    
    public AgenciaEdicaoCommand(){
        
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void validate() {
    	CommandValidator<AgenciaEdicaoCommand> validator =
        		new CommandValidator<AgenciaEdicaoCommand>();
        validator.validate(this);
    }
}
