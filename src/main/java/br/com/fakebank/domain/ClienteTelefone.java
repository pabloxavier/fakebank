package br.com.fakebank.domain;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.fakebank.domain.commands.ClienteTelefoneEdicaoCommand;
import br.com.fakebank.domain.commands.ClienteTelefoneInclusaoCommand;
import br.com.fakebank.infrastructure.converters.TipoTelefoneConverter;

@Entity
@Table(name= "cliente_telefone", schema= "dbo")
public class ClienteTelefone {

    @EmbeddedId
    private ClienteTelefoneId clienteTelefoneId;
    
    @Column(name= "nr_prefixo")
    private Short prefixo;
    
    @Column(name= "nr_telefone")
    private Integer numero;
    
    @Column(name = "cd_tipo_telefone")
    @Convert(converter = TipoTelefoneConverter.class)
    private TipoTelefone tipoTelefone;

    public ClienteTelefone() {
        
    }

    public static ClienteTelefone criar(
            Cliente cliente,
            ClienteTelefoneInclusaoCommand comando,
            Short codigoTelefone) {

        comando.validate();

        ClienteTelefone telefone = new ClienteTelefone();
        telefone.tipoTelefone = comando.getTipoTelefone();
        telefone.prefixo = comando.getPrefixo();
        telefone.numero = comando.getTelefone();
        telefone.clienteTelefoneId = new ClienteTelefoneId(cliente.getCodigo(), codigoTelefone);

        return telefone;
    }

    
    public void editar(ClienteTelefoneEdicaoCommand comando){
        	
    	comando.validate();
    	
    	this.prefixo = comando.getPrefixo();
    	this.numero = comando.getTelefone();
    	this.tipoTelefone = comando.getTipoTelefone();
  }
    
    
    public ClienteTelefoneId getClienteTelefoneId() {
        return clienteTelefoneId;
    }

    public Short getPrefixo() {
        return prefixo;
    }

    public Integer getNumero() {
        return numero;
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

}
