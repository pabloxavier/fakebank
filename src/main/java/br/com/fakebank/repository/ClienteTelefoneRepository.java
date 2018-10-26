package br.com.fakebank.repository;

import br.com.fakebank.domain.ClienteTelefone;
import br.com.fakebank.domain.ClienteTelefoneId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ClienteTelefoneRepository extends JpaRepository<ClienteTelefone, ClienteTelefoneId>,
										   JpaSpecificationExecutor<ClienteTelefone>{

	@Query("select max(ct.clienteTelefoneId.codigoTelefone) from ClienteTelefone ct where ct.clienteTelefoneId.codigoCliente = ?1")
	Short getUltimoCodigoTelefoneFromCliente(Integer codigoCliente);

}
