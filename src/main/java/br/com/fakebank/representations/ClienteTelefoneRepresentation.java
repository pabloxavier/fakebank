package br.com.fakebank.representations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;

import br.com.fakebank.common.util.ListaPaginada;
import br.com.fakebank.domain.ClienteTelefone;
import br.com.fakebank.domain.converters.TelefoneCoverter;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"sequencia","prefixo", "numero", "numeroCompleto", "tipo" })
public class ClienteTelefoneRepresentation {

	private Short sequencia;
    private Short prefixo;
    private String numero; 
    private String numeroCompleto;
    private String tipo;

    public static ClienteTelefoneRepresentation from(ClienteTelefone clienteTelefone) {
    	
    	TelefoneCoverter telefoneConverter = new TelefoneCoverter();
    	String numeroFormatado = telefoneConverter.convertToNumeroFormatado(clienteTelefone.getNumero());
    	String numeroFormatadoCompleto = telefoneConverter.convertToNumeroCompletoFormatado(clienteTelefone.getPrefixo(), clienteTelefone.getNumero());
    	
        ClienteTelefoneRepresentation model = new ClienteTelefoneRepresentation();
        model.setSequencia(clienteTelefone.getClienteTelefoneId().getCodigoTelefone());
        model.setPrefixo(clienteTelefone.getPrefixo());
        model.setNumero(numeroFormatado);
        model.setNumeroCompleto(numeroFormatadoCompleto);
        model.setTipo(StringUtils.capitalize(clienteTelefone.getTipoTelefone().toString().toLowerCase()));
        return model;
    }

    public static List<ClienteTelefoneRepresentation> from(List<ClienteTelefone> clienteTelefones) {
        List<ClienteTelefoneRepresentation> telefones = new ArrayList<ClienteTelefoneRepresentation>();
        for (ClienteTelefone tel : clienteTelefones) {
            telefones.add(from(tel));
        }
        return telefones;
    }

	public static ListaPaginada<ClienteTelefoneRepresentation> from(Page<ClienteTelefone> clienteTelefones){

		ListaPaginada<ClienteTelefoneRepresentation> lista =
				new ListaPaginada<ClienteTelefoneRepresentation>();
		lista.setContent(clienteTelefones
				.stream()
				.map(item -> from(item))
				.collect(Collectors.toList()));

		lista.setTotalPages(clienteTelefones.getTotalPages());
		lista.setPageNumber(clienteTelefones.getPageable().getPageNumber());
		lista.setPageSize(clienteTelefones.getPageable().getPageSize());

		return lista;
	}

	public Short getSequencia() {
		return sequencia;
	}

	public void setSequencia(Short sequencia) {
		this.sequencia = sequencia;
	}

	public Short getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(Short prefixo) {
		this.prefixo = prefixo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumeroCompleto() {
		return numeroCompleto;
	}

	public void setNumeroCompleto(String numeroCompleto) {
		this.numeroCompleto = numeroCompleto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
    
}
