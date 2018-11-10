package br.com.fakebank.representations;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.fakebank.domain.Agencia;

@ApiModel(description = "Classe de modelo (representação de agência).")
public class AgenciaRepresentationV1 {

	@ApiModelProperty(notes = "Código da agência (autonumerável).")
    private Integer codigo;
	@ApiModelProperty(notes = "Número de identificação da agência.")
    private Integer numero;
	@ApiModelProperty(notes = "Nome fantasia.")
    private String nome;
	@ApiModelProperty(notes = "CNPJ da agência.")
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
