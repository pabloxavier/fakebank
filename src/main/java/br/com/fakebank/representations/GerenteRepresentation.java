package br.com.fakebank.representations;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import br.com.fakebank.domain.Pessoa;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.fakebank.domain.Gerente;
import br.com.fakebank.util.ListaPaginada;

@ApiModel(description = "Classe de Gerentes (representação de gerentes).")
public class GerenteRepresentation {

	@ApiModelProperty(notes = "Código gerente (autonumerável).")
    private Integer codigo;

	@ApiModelProperty(notes = "Código da pessoa.")
    private Integer codPessoa;
	
	@ApiModelProperty (notes = "Dados da pessoa.")
	private Pessoa objPessoa;
	
	@ApiModelProperty(notes = "Gerente ativo/inativo")
    private boolean ativo;
	
    public static GerenteRepresentation from(Gerente gerente){
        GerenteRepresentation model = new GerenteRepresentation();
        model.codigo    = gerente.getCodGerente();
		model.objPessoa = gerente.getPessoa();
        model.ativo     = gerente.isAtivo();
        return model;
    }
    
    public static List<GerenteRepresentation> from(List<Gerente> Gerentes){
    	return
    		Gerentes
    			.stream()
    			.map(item -> from(item))
    			.collect(Collectors.toList());
    }
    
    public static ListaPaginada<GerenteRepresentation> from(Page<Gerente> Gerentes){
        
        ListaPaginada<GerenteRepresentation> lista =
                new ListaPaginada<GerenteRepresentation>();

        lista.setContent(Gerentes
                			.stream()
                			.map(item -> from(item))
                			.collect(Collectors.toList()));
        
        lista.setTotalPages(Gerentes.getTotalPages());
        lista.setPageNumber(Gerentes.getPageable().getPageNumber());
        lista.setPageSize(Gerentes.getPageable().getPageSize());
        
        return lista;
    }
    
    public Integer getCodigo() {
        return codigo;
    }
    public Integer getCodPessoa() {
        return codPessoa;
    }
	
	public Pessoa getPessoa () {
	 return objPessoa;
	}
    
    public boolean isAtivo() {
        return ativo;
    }	
}