package br.com.fakebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fakebank.domain.Dominio;

public interface DominioRepository extends JpaRepository<Dominio, Integer>, JpaSpecificationExecutor<Dominio> {

}
