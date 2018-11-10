package br.com.fakebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fakebank.domain.SituacaoConta;

public interface SituacaoContaRepository extends JpaRepository<SituacaoConta, Integer>, JpaSpecificationExecutor<SituacaoConta> {

}
