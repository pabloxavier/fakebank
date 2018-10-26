package br.com.fakebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fakebank.domain.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer>, JpaSpecificationExecutor<Conta>{

	
}
