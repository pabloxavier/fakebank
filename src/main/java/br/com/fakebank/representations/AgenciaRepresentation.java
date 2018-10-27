package br.com.fakebank.representations;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fakebank.domain.Agencia;

public class AgenciaRepresentation {

    private Integer codigoDaAgencia;
    private String nomeCompleto;
    private String cnpj;
    
    public static AgenciaRepresentation from(Agencia agencia){
        AgenciaRepresentation model = new AgenciaRepresentation();
        model.setCodigoDaAgencia(agencia.getCodigo());
        model.setNomeCompleto(agencia.getNome());
        model.setCnpj(agencia.getCnpj());
        return model;
    }
    
    public static List<AgenciaRepresentation> from(List<Agencia> agencias){
    	return
    		agencias
    			.stream()
    			.map(item -> from(item))
    			.collect(Collectors.toList());
    }
    
    public Integer getCodigoDaAgencia() {
        return codigoDaAgencia;
    }
    public void setCodigoDaAgencia(Integer codigoDaAgencia) {
        this.codigoDaAgencia = codigoDaAgencia;
    }
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
