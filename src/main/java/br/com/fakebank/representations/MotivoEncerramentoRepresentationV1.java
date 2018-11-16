package br.com.fakebank.representations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.fakebank.domain.MotivoEncerramento;
import br.com.fakebank.util.ListaPaginada;
import io.swagger.annotations.ApiModel;

@ApiModel(description = "Classe de modelo (representação de motivo encerramento).")
public class MotivoEncerramentoRepresentationV1 {

	private Integer identificador;
	private Integer codigo;
	private String descricao;
    
    public static MotivoEncerramentoRepresentationV1 from(MotivoEncerramento motivo){
        MotivoEncerramentoRepresentationV1 model = new MotivoEncerramentoRepresentationV1();
        model.setIdentificador(motivo.getCodigo());
        model.setCodigo(Integer.valueOf(motivo.getValor()));
        model.setDescricao(motivo.getDescricao());
        return model;
    }
    
    public static List<MotivoEncerramentoRepresentationV1> from(List<MotivoEncerramento> motivo){
    	return
    		motivo
    			.stream()
    			.map(item -> from(item))
    			.collect(Collectors.toList());
    }
    
    public static ListaPaginada<MotivoEncerramentoRepresentationV1> from(Page<MotivoEncerramento> motivo){
        
        ListaPaginada<MotivoEncerramentoRepresentationV1> lista =
                new ListaPaginada<MotivoEncerramentoRepresentationV1>();

        lista.setContent(motivo
                			.stream()
                			.map(item -> from(item))
                			.collect(Collectors.toList()));
        
        lista.setTotalPages(motivo.getTotalPages());
        lista.setPageNumber(motivo.getPageable().getPageNumber());
        lista.setPageSize(motivo.getPageable().getPageSize());
        
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
