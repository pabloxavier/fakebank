package br.com.fakebank.representations;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.fakebank.domain.TipoConta;
import br.com.fakebank.util.ListaPaginada;

public class TipoContaRepresentation {

    private Integer identificador;
    private Integer codigo;
    private String descricao;
    
    public static TipoContaRepresentation from(TipoConta tipoConta){
        TipoContaRepresentation model = new TipoContaRepresentation();
        model.setIdentificador(tipoConta.getCodigo());
        model.setCodigo(Integer.valueOf(tipoConta.getValor()));
        model.setDescricao(tipoConta.getDescricao());
        return model;
    }
    
    public static ListaPaginada<TipoContaRepresentation> from(Page<TipoConta> tiposConta){
        
        ListaPaginada<TipoContaRepresentation> lista = new ListaPaginada<TipoContaRepresentation>();

        lista.setContent(tiposConta
                			.stream()
                			.map(item -> from(item))
                			.collect(Collectors.toList()));
        
        lista.setTotalPages(tiposConta.getTotalPages());
        lista.setPageNumber(tiposConta.getPageable().getPageNumber());
        lista.setPageSize(tiposConta.getPageable().getPageSize());
        
        return lista;
    }

	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
}
