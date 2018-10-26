package br.com.fakebank.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClienteTelefoneId implements Serializable {

    @Column(name = "cd_cliente")
    private Integer cdCliente;

    @Column(name = "cd_telefone")
    private Short codigo;

    public ClienteTelefoneId() {
    }

    public ClienteTelefoneId(Integer cdCliente, Short codigo) {
        this.cdCliente = cdCliente;
        this.codigo = codigo;
    }

    public Integer getCdCliente() {
        return cdCliente;
    }

    public Short getCodigo() {
        return codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteTelefoneId)) return false;
        ClienteTelefoneId that = (ClienteTelefoneId) o;
        return Objects.equals(getCdCliente(), that.getCdCliente()) &&
                Objects.equals(getCodigo(), that.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCdCliente(), getCodigo());
    }
}
