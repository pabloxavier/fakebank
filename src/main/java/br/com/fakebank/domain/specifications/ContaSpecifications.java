package br.com.fakebank.domain.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.com.fakebank.domain.Conta;

public class ContaSpecifications {

    public static Specification<Conta> porParteNomeCliente(String nomeCliente){
        return (root, criteriaQuery, criteriaBuilder) -> 
            criteriaBuilder.like(root.get("pessoa").get("nome"), "%" + nomeCliente + "%");
    }
    
    public static Specification<Conta> porTipoConta(Integer tipoConta){
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("tipoConta"), tipoConta);
    }
    
    public static Specification<Conta> porCodigo(String codigo){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("codigo"), codigo);
    }
}
