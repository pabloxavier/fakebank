package br.com.fakebank.repository;

import br.com.fakebank.domain.ClienteTelefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClienteTelefoneRepository extends JpaRepository<ClienteTelefone, Integer>,
										   JpaSpecificationExecutor<ClienteTelefone>{

}
