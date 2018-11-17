package br.com.fakebank.representations;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.fakebank.common.util.ListaPaginada;
import br.com.fakebank.domain.SituacaoConta;

public class SituacaoContaRepresentation {

    private Integer identificador;
    private Integer codigo;
    private String descricao;
    
    public static SituacaoContaRepresentation from(SituacaoConta situacaoConta){
        SituacaoContaRepresentation model = new SituacaoContaRepresentation();
        model.setIdentificador(situacaoConta.getCodigo());
        model.setCodigo(Integer.valueOf(situacaoConta.getValor()));
        model.setDescricao(situacaoConta.getDescricao());
        return model;
    }
    
    public static ListaPaginada<SituacaoContaRepresentation> from(Page<SituacaoConta> situacoesConta){
        
        ListaPaginada<SituacaoContaRepresentation> lista = new ListaPaginada<SituacaoContaRepresentation>();

        lista.setContent(situacoesConta
                			.stream()
                			.map(item -> from(item))
                			.collect(Collectors.toList()));
        
        lista.setTotalPages(situacoesConta.getTotalPages());
        lista.setPageNumber(situacoesConta.getPageable().getPageNumber());
        lista.setPageSize(situacoesConta.getPageable().getPageSize());
        
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
