package br.com.fakebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fakebank.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>,
										   JpaSpecificationExecutor<Cliente>{

}
