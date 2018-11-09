package br.com.fakebank.representations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.fakebank.domain.Agencia;

public class AgenciaRepresentationV1 {

    private Integer codigo;
    private Integer numero;
    private String nome;
    private String cnpj;
    
    public static AgenciaRepresentationV1 from(Agencia agencia){
        AgenciaRepresentationV1 model = new AgenciaRepresentationV1();
        model.codigo = agencia.getCodigo();
        model.numero = agencia.getNumero();
        model.nome = agencia.getNome();
        model.cnpj = agencia.getCnpj();
        return model;
    }
    
    public static List<AgenciaRepresentationV1> from(List<Agencia> agencias){
    	return
    		agencias
    			.stream()
    			.map(item -> from(item))
    			.collect(Collectors.toList());
    }
    
    public static List<AgenciaRepresentationV1> from(Page<Agencia> agencias){
    	return
    		agencias
    			.stream()
    			.map(item -> from(item))
    			.collect(Collectors.toList());
    }
    
    public Integer getCodigo() {
        return codigo;
    }
    public Integer getNumero() {
        return numero;
    }
    public String getNome() {
        return nome;
    }
    public String getCnpj() {
        return cnpj;
    }
}
