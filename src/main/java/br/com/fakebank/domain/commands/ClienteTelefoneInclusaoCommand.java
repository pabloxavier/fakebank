package br.com.fakebank.domain.commands;

import br.com.fakebank.common.validations.CommandValidator;
import br.com.fakebank.common.validations.FieldName;
import br.com.fakebank.domain.TipoTelefone;

import javax.validation.constraints.NotNull;

public class ClienteTelefoneInclusaoCommand {

    @FieldName("prefixo")
    @NotNull
    private Short prefixo;

    @FieldName("telefone")
    @NotNull
    private Integer telefone;

    @FieldName("tipo")
    @NotNull
    private TipoTelefone tipo;

    public ClienteTelefoneInclusaoCommand() {

    }

    public Short getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(Short prefixo) {
        this.prefixo = prefixo;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    public void validate() {

        CommandValidator<ClienteTelefoneInclusaoCommand> validator =
                new CommandValidator<>();

        validator.validate(this);
    }
}
