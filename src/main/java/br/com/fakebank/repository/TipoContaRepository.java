package br.com.fakebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fakebank.domain.Dominio;
import br.com.fakebank.domain.TipoConta;

public interface TipoContaRepository extends JpaRepository<TipoConta, Integer>, JpaSpecificationExecutor<TipoConta> {

}
