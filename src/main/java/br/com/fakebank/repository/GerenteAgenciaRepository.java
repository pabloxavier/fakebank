package br.com.fakebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fakebank.domain.GerenteAgencia;

public interface GerenteAgenciaRepository 
		extends JpaRepository<GerenteAgencia, Integer>,
				JpaSpecificationExecutor<GerenteAgencia> {
	
}
