package br.com.fakebank.domain.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.com.fakebank.domain.GerenteAgencia;

public class GerenteAgenciaSpecifications {
	 
	public static Specification<GerenteAgencia> porSituacao(boolean isAtivo) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get("isAtivo"), isAtivo);
	}
}
