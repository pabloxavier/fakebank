package br.com.fakebank.domain.commands;

import br.com.fakebank.common.validations.FieldName;
import br.com.fakebank.domain.Pessoa;

import javax.validation.constraints.NotNull;

public class GerenteAgenciaInclusaoCommand {

	// @fieldname -> complementa a mensagem de "@NotNull"
    @FieldName("codAgencia")
    @NotNull
    private Integer codAgencia;

    // @fieldname -> complementa a mensagem de "@NotNull"
    @FieldName("codGerente")
    @NotNull
    private Integer codGerente;
    
    private Pessoa  pessoaGerente;

    public GerenteAgenciaInclusaoCommand() {

    }

    public Pessoa getPessoa () {
    	return pessoaGerente;
    }
    public void setPessoa(Pessoa argPessoa) {
        pessoaGerente = argPessoa;
    }

    public void setCodAgencia(Integer argCodAgencia) {
        this.codAgencia = argCodAgencia;
    }

    public Integer getCodGerente() {
        return codGerente;
    }
}
