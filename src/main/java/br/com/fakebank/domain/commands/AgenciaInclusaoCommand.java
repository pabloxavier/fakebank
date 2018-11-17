package br.com.fakebank.domain.commands;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import br.com.fakebank.common.validations.CommandValidator;
import br.com.fakebank.common.validations.FieldName;
import br.com.fakebank.domain.validators.AgenciaUniqueCnpj;
import br.com.fakebank.domain.validators.CnpjValid;

public class AgenciaInclusaoCommand {

    @NotNull
    @Range(min = 1, max = 9999)
    @FieldName("Número")
	@ApiModelProperty(notes = "Número de identificação da agência.")
    private Integer numero;
    
    @NotNull(message = "{agencia.nome.nao.nulo}")
    @NotBlank(message = "{agencia.nome.nao.vazio}")
	@ApiModelProperty(notes = "Nome da agência.")
    private String nome;
    
    @NotNull
    @NotBlank
    @CnpjValid
    @AgenciaUniqueCnpj
	@ApiModelProperty(notes = "CNPJ da agência. (Deve ser único)")
    private String cnpj;
    
    public AgenciaInclusaoCommand(){
        
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public void validate() {
        
    	CommandValidator<AgenciaInclusaoCommand> validator =
        		new CommandValidator<AgenciaInclusaoCommand>();
        
        validator.validate(this);
    }
}
