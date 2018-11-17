package br.com.fakebank.domain.commands;

import br.com.fakebank.domain.Pessoa;

public class GerenteEdicaoCommand {

    private boolean ativo;
    private Pessoa pessoa;

    protected GerenteEdicaoCommand() {
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean isAtivo) {
        this.ativo = isAtivo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}