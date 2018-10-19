package br.com.fakebank.domain.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.com.fakebank.domain.Pessoa;

public class PessoaSpecifications {

	public static Specification<Pessoa> porParteNome(String nome){
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
	}
	
	public static Specification<Pessoa> porParteDocumento(String documento){
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("documento"), "%" + documento + "%");
	}
	
	public static Specification<Pessoa> porTipo(String tipo){
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get("tipoPessoa"), tipo);
	}
	
}
