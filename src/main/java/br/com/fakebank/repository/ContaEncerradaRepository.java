package br.com.fakebank.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fakebank.domain.ContaEncerrada;

public interface ContaEncerradaRepository extends JpaRepository<ContaEncerrada, Integer>, 
										  JpaSpecificationExecutor<ContaEncerrada>{

}
