package br.com.fakebank.domain.commands;

import br.com.fakebank.domain.TipoTelefone;
import br.com.fakebank.domain.validators.CommandValidator;
import br.com.fakebank.exceptions.FieldName;

import javax.validation.constraints.NotNull;

public class ClienteTelefoneInclusaoCommand {

    @FieldName("prefixo")
    @NotNull
    private Short prefixo;

    @FieldName("telefone")
    @NotNull
    private Integer telefone;

    @FieldName("tipoTelefone")
    @NotNull
    private TipoTelefone tipoTelefone;

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

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public void validate() {

        CommandValidator<ClienteTelefoneInclusaoCommand> validator =
                new CommandValidator<>();

        validator.validate(this);
    }
}
