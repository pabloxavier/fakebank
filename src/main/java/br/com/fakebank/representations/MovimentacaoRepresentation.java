package br.com.fakebank.representations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.fakebank.domain.Movimentacao;
import br.com.fakebank.util.ListaPaginada;

public class MovimentacaoRepresentation {

    private Integer codigoMovimentacao;
    private String codigoConta;
    private LocalDate dataMovimentacao;
    private double valorMovimentacao;
    private Integer codigoTipoMovimentacao;
    private double valorSaldoAtual;

    public static MovimentacaoRepresentation from(Movimentacao movimentacao) {
        MovimentacaoRepresentation model = new MovimentacaoRepresentation();
        model.setCodigoMovimentacao(movimentacao.getCodigoMovimentacao());
        model.setCodigoConta(movimentacao.getCodigoConta());
        model.setDataMovimentacao(movimentacao.getDataMovimentacao());
        model.setValorMovimentacao(movimentacao.getValorMovimentacao());
        model.setCodigoTipoMovimentacao(movimentacao.getCodigoTipoMovimentacao());
        model.setValorSaldoAtual(movimentacao.getValorSaldoAtual());
        return model;
    }
    
    public static List<MovimentacaoRepresentation> from(List<Movimentacao> movimentacao) {
        List<MovimentacaoRepresentation> list = new ArrayList<>();
        for(Movimentacao e : movimentacao) {
            MovimentacaoRepresentation model = new MovimentacaoRepresentation();
            model.setCodigoMovimentacao(e.getCodigoMovimentacao());
            model.setCodigoConta(e.getCodigoConta());
            model.setDataMovimentacao(e.getDataMovimentacao());
            model.setValorMovimentacao(e.getValorMovimentacao());
            model.setCodigoTipoMovimentacao(e.getCodigoTipoMovimentacao());
            model.setValorSaldoAtual(e.getValorSaldoAtual());
            list.add(model);
        }
        return list;
    }
    
    public static ListaPaginada<MovimentacaoRepresentation> from (Page<Movimentacao> movimentacoes){
    	
    	ListaPaginada<MovimentacaoRepresentation> lista = new ListaPaginada<MovimentacaoRepresentation>();
    	
    	lista.setContent(movimentacoes
    								.stream()
    								.map(item -> from(item))
    								.collect(Collectors.toList()));
    	
    	lista.setTotalPages(movimentacoes.getTotalPages());
    	lista.setPageNumber(movimentacoes.getPageable().getPageNumber());
    	lista.setPageSize(movimentacoes.getPageable().getPageSize());
    	
    	return lista;
    	
    }

    public Integer getCodigoMovimentacao() {
        return codigoMovimentacao;
    }

    public void setCodigoMovimentacao(Integer codigoMovimentacao) {
        this.codigoMovimentacao = codigoMovimentacao;
    }

    public String getCodigoConta() {
        return codigoConta;
    }

    public void setCodigoConta(String codigoConta) {
        this.codigoConta = codigoConta;
    }

    public LocalDate getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDate dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public double getValorMovimentacao() {
        return valorMovimentacao;
    }

    public void setValorMovimentacao(double valorMovimentacao) {
        this.valorMovimentacao = valorMovimentacao;
    }

    public Integer getCodigoTipoMovimentacao() {
        return codigoTipoMovimentacao;
    }

    public void setCodigoTipoMovimentacao(Integer codigoTipoMovimentacao) {
        this.codigoTipoMovimentacao = codigoTipoMovimentacao;
    }

    public double getValorSaldoAtual() {
        return valorSaldoAtual;
    }

    public void setValorSaldoAtual(double valorSaldoAtual) {
        this.valorSaldoAtual = valorSaldoAtual;
    }
}
