package br.com.fakebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fakebank.domain.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, Integer>, JpaSpecificationExecutor<Gerente> {


}
