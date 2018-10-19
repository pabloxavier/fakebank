package br.com.fakebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fakebank.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>,
                                          JpaSpecificationExecutor<Pessoa>{

}
