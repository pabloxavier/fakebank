package br.com.fakebank.domain.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.TipoPessoa;

public class ClienteSpecifications {

    //WHERE ds_endereco_completo like '%endereco%'
    public static Specification<Cliente> clientePorParteEndereco(String endereco){
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.like(root.get("endereco"), "%" + endereco + "%");
    }
    
    //WHERE is_ativo = true
    public static Specification<Cliente> clientePorSituacao(boolean isAtivo){
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("isAtivo"), isAtivo);
    }
    
    //WHERE cd_cliente = codigo
    public static Specification<Cliente> clientePorCodigo(Integer codigo){
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("codigo"), codigo);
    }
    
    //WHERE pessoa.TipoPessoa = tipoPessoa
    public static Specification<Cliente> clientePorTipo(TipoPessoa tipoPessoa){
        return (root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("pessoa").get("tipoPessoa"), tipoPessoa);
    }
        
}