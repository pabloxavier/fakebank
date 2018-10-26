package br.com.fakebank.domain.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.com.fakebank.domain.Cliente;

public class ClienteSpecifications {

	public static Specification<Cliente> porParteEndereco(String endereco){
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("endereco"), "%" + endereco + "%");
	}
	
	public static Specification<Cliente> porSituacao(boolean isAtivo){
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("isAtivo"), isAtivo);
	}
	
	public static Specification<Cliente> porCodigoCliente(Integer codigo){
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get("codigo"), codigo);
	}
}
