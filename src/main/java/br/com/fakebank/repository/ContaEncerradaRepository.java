package br.com.fakebank.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import src.main.java.br.com.fakebank.domain.ContaEncerrada;

public interface ContaEncerradaRepository extends JpaRepository<ContaEncerrada, Integer>, 
										  JpaSpecificationExecutor<ContaEncerrada>{

}
