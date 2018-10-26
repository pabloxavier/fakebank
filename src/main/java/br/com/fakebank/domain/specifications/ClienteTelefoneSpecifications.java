package br.com.fakebank.domain.specifications;

import br.com.fakebank.domain.ClienteTelefone;
import org.springframework.data.jpa.domain.Specification;

public class ClienteTelefoneSpecifications {

	public static Specification<ClienteTelefone> porCodigoCliente(Integer codigo){
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get("clienteTelefoneId").get("codigoCliente"), codigo);
	}
	
}
