package br.com.fakebank.domain.commands;

import br.com.fakebank.domain.TipoTelefone;

import javax.validation.constraints.NotNull;

public class ClienteTelefoneInclusaoCommand {

    @NotNull
    private Short nrPrefixo;

    @NotNull
    private Integer nrTelefone;

    @NotNull
    private TipoTelefone tipoTelefone;

    public ClienteTelefoneInclusaoCommand() {

    }

    public Short getNrPrefixo() {
        return nrPrefixo;
    }

    public void setNrPrefixo(Short nrPrefixo) {
        this.nrPrefixo = nrPrefixo;
    }

    public Integer getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(Integer nrTelefone) {
        this.nrTelefone = nrTelefone;
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }
}
