package br.com.fakebank.domain.commands;

import br.com.fakebank.domain.TipoTelefone;

public class ClienteTelefoneInclusaoCommand {

    private Short nrPrefixo;
    private Integer nrTelefone;
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
