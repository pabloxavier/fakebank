package br.com.fakebank.domain.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.com.fakebank.domain.Gerente;

public class GerenteSpecifications {

    public static Specification<Gerente> porSituacao(boolean isAtivo){
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("isAtivo"), isAtivo);
    }

}
