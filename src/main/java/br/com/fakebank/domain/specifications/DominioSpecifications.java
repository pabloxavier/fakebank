package br.com.fakebank.domain.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.com.fakebank.domain.Dominio;

public class DominioSpecifications {

    public static Specification<Dominio> dominioPorValor(String valor){
        return (root, criteriaQuery, criteriaBuilder) -> 
        criteriaBuilder.equal(root.get("valor"), valor);
    }

    public static Specification<Dominio> dominioPorTipo(String tipo){
        return (root, criteriaQuery, criteriaBuilder) -> 
        criteriaBuilder.equal(root.get("tipo"), tipo);
    }

}
