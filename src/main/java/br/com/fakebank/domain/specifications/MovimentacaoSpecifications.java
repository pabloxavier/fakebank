package br.com.fakebank.domain.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.fakebank.domain.Movimentacao;

public class MovimentacaoSpecifications {

    //WHERE cd_conta = codigo
    public static Specification<Movimentacao> movimentacaoPorCodigoConta(String codigo){
        return (root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("codigoConta"), codigo);
    }

    //WHERE dt_movimentacao = data
    public static Specification<Movimentacao> movimentacaoPorData(LocalDate data){
        return (root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("dataMovimentacao"), data);
    }

    //WHERE dt_movimentacao > inicio && dt_movimentacao < fim
    public static Specification<Movimentacao> movimentacaoPorPeriodo(LocalDate inicio, LocalDate fim){
        return (root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.between(root.get("dataMovimentacao"), inicio, fim);
    }

    //WHERE vl_movimentacao = valor
    public static Specification<Movimentacao> movimentacaoPorValor(double valor){
        return (root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("valorMovimentacao"), valor);
    }

    //WHERE cd_tipo_movimentacao = tipo
    public static Specification<Movimentacao> movimentacaoPorTipo(Integer tipo){
        return (root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("codigoTipoMovimentacao"), tipo);
    }

}
