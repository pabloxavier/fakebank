package br.com.fakebank.domain.commands;

import br.com.fakebank.domain.TipoTelefone;

import javax.validation.constraints.NotNull;

public class ClienteTelefoneInclusaoCommand {

    @NotNull
    private Short prefixo;

    @NotNull
    private Integer telefone;

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
}
