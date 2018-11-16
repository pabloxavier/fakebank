package br.com.fakebank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Dominio;
import br.com.fakebank.domain.specifications.DominioSpecifications;
import br.com.fakebank.repository.DominioRepository;

@Service
public class DominioService {

    @Autowired
    DominioRepository repository;
    
    public DominioService() {
        
    }
   
    public boolean dominioExiste(Integer valor, String tipo){
		Specification<Dominio> criterio = Specification
				.where(DominioSpecifications.dominioPorValor(valor)
				.and(DominioSpecifications.dominioPorTipo(tipo)));
		Optional<Dominio> dominio = repository.findOne(criterio);
        return dominio.isPresent() ? true : false;    
    }


}
