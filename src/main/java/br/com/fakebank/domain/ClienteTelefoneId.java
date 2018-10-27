package br.com.fakebank.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClienteTelefoneId implements Serializable {

    @Column(name = "cd_cliente")
    private Integer codigoCliente;

    @Column(name = "cd_telefone")
    private Short codigoTelefone;

    public ClienteTelefoneId() {
    }

    public ClienteTelefoneId(Integer codigoCliente, Short codigoTelefone) {
        this.codigoCliente = codigoCliente;
        this.codigoTelefone = codigoTelefone;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public Short getCodigoTelefone() {
        return codigoTelefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteTelefoneId)) return false;
        ClienteTelefoneId that = (ClienteTelefoneId) o;
        return Objects.equals(getCodigoCliente(), that.getCodigoCliente()) &&
                Objects.equals(getCodigoTelefone(), that.getCodigoTelefone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoCliente(), getCodigoTelefone());
    }
}
