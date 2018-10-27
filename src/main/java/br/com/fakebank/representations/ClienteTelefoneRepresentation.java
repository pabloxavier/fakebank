package br.com.fakebank.representations;

import br.com.fakebank.domain.ClienteTelefone;
import br.com.fakebank.domain.TipoTelefone;

import java.util.ArrayList;
import java.util.List;

public class ClienteTelefoneRepresentation {

    private Integer codigoCliente;
    private Short codigoTelefone;
    private Short prefixo;
    private Integer numero;
    private TipoTelefone tipoTelefone;

    public static ClienteTelefoneRepresentation from(ClienteTelefone clienteTelefone) {
        ClienteTelefoneRepresentation model = new ClienteTelefoneRepresentation();
        model.setCodigoCliente(clienteTelefone.getClienteTelefoneId().getCodigoCliente());
        model.setCodigoTelefone(clienteTelefone.getClienteTelefoneId().getCodigoTelefone());
        model.setPrefixo(clienteTelefone.getPrefixo());
        model.setNumero(clienteTelefone.getNumero());
        model.setTipoTelefone(clienteTelefone.getTipoTelefone());
        return model;
    }

    public static List<ClienteTelefoneRepresentation> from(List<ClienteTelefone> clienteTelefones) {
        List<ClienteTelefoneRepresentation> telefones = new ArrayList<ClienteTelefoneRepresentation>();
        for (ClienteTelefone tel : clienteTelefones) {
            telefones.add(from(tel));
        }
        return telefones;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Short getCodigoTelefone() {
        return codigoTelefone;
    }

    public void setCodigoTelefone(Short codigoTelefone) {
        this.codigoTelefone = codigoTelefone;
    }

    public Short getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(Short prefixo) {
        this.prefixo = prefixo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }
}
