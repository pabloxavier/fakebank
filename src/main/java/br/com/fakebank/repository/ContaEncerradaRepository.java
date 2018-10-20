package br.com.fakebank.repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaEncerradaRepository extends JpaRepository<ContaEncerrada, Integer>,
											  JpaSpecificationExecutor<ContaEncerrada>{

}
