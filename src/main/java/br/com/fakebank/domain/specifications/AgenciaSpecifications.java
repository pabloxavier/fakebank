package br.com.fakebank.domain.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.com.fakebank.domain.Agencia;

public class AgenciaSpecifications {

    //WHERE nr_agencia = numero
    public static Specification<Agencia> agenciaPorNumero(Integer numero){
        return (root, criteriaQuery, criteriaBuilder) -> 
        criteriaBuilder.equal(root.get("numero"), numero);
    }

    //WHERE nr_cnpj = cnpj
    public static Specification<Agencia> agenciaPorCnpj(String cnpj){
        return (root, criteriaQuery, criteriaBuilder) -> 
        criteriaBuilder.equal(root.get("cnpj"), cnpj);
    }

    //WHERE nm_agencia like '%nome%'
    public static Specification<Agencia> agenciaPorParteNome(String nome){
        return (root, criteriaQuery, criteriaBuilder) -> 
        criteriaBuilder.like(root.get("nome"), "%" + nome.toUpperCase() + "%");
    }
    
    //WHERE nr_cnpj like '%cnpj%'
    public static Specification<Agencia> agenciaPorParteCnpj(String cnpj){
        return (root, criteriaQuery, criteriaBuilder) -> 
        criteriaBuilder.like(root.get("cnpj"), "%" + cnpj + "%");
    }
    
}
