package br.com.fakebank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.ClienteTelefone;
import br.com.fakebank.domain.ClienteTelefoneId;
import br.com.fakebank.domain.TipoPessoa;
import br.com.fakebank.domain.commands.ClienteTelefoneEdicaoCommand;
import br.com.fakebank.domain.commands.ClienteTelefoneInclusaoCommand;
import br.com.fakebank.domain.specifications.ClienteSpecifications;
import br.com.fakebank.domain.specifications.ClienteTelefoneSpecifications;
import br.com.fakebank.exceptions.NotFoundException;
import br.com.fakebank.repository.ClienteRepository;
import br.com.fakebank.repository.ClienteTelefoneRepository;

@Service
@CacheConfig(cacheNames = "clientes")
public class ClienteService {


	@Autowired
	private ClienteRepository repository;

	@Autowired
	private ClienteTelefoneRepository telefoneRepository;

	public Page<Cliente> listar(Pageable pageable) {

		Specification<Cliente> criterio = Specification.where(ClienteSpecifications.clientePorTipo(TipoPessoa.FISICA));

		Page<Cliente> clientes = repository.findAll(criterio, pageable);

		if (clientes.getSize() == 0)
			throw new NotFoundException();

		return clientes;
	}

	@Cacheable(key = "#codigo")
	public Cliente consultarPorCodigo(Integer codigo) {
		return repository.findById(codigo).orElseThrow(() -> new NotFoundException());
	}

	public List<Cliente> filtrar(String endereco, boolean ativo, Integer codigo) {
		Specification<Cliente> criterio = Specification.where(ClienteSpecifications.clientePorParteEndereco(endereco)
				.and(ClienteSpecifications.clientePorSituacao(ativo))
				.and(ClienteSpecifications.clientePorCodigo(codigo))
				.and(ClienteSpecifications.clientePorTipo(TipoPessoa.FISICA)));

		List<Cliente> clientes = repository.findAll(criterio);

		if (clientes.isEmpty())
			throw new NotFoundException();

		return clientes;
	}

	/// salvar é nos especificos PF e PJ
	// @CachePut
	// public Agencia salvar(AgenciaInclusaoCommand comando){
	// Agencia agencia = Agencia.criar(comando);
	// return repository.save(agencia);
	// }

	// @CachePut(key = "#codigo")
	// public Agencia salvar(Integer codigo, AgenciaEdicaoCommand comando){
	// Agencia agencia = consultarPorCodigo(codigo);
	// agencia.editar(comando);
	// return repository.save(agencia);
	// }

	// @CacheEvict(key = "#codigo")
	// public void excluir(Integer codigo){
	// Cliente cliente = consultarPorCodigo(codigo);
	// repository.deleteById(cliente.getCodigo());
	// }

	// public boolean cnpjJaExiste(String cnpj){
	// Specification<Agencia> criterio =
	// AgenciaSpecifications.agenciaPorParteCnpj(cnpj);
	// Optional<Agencia> agencia = repository.findOne(criterio);
	//
	// return agencia.isPresent();
	// }

	@CacheEvict(allEntries = true, cacheNames = "clientes")
	@Scheduled(fixedDelay = 120000)
	public void liberarCache() {
		System.out.println("Liberação de cache de clientes: " + new Date());
	}

	public Cliente getClienteById(Integer codigo) {
		return repository.findById(codigo).orElse(null);
	}

	public List<Cliente> filtrar(String endereco, boolean isAtivo) {
		Specification<Cliente> criterio = Specification.where(ClienteSpecifications.clientePorParteEndereco(endereco)
				.and(ClienteSpecifications.clientePorSituacao(isAtivo)));
		return repository.findAll(criterio);
	}

	public boolean excluir(Integer codigo) {
		Cliente cliente = getClienteById(codigo);

		if (cliente == null)
			return false;

		repository.deleteById(codigo);
		return true;
	}

	private Short getProximoCodigoTelefoneFromCliente(Integer codigoCliente) {
		Short codigoTelefone = telefoneRepository.getUltimoCodigoTelefoneFromCliente(codigoCliente);

		return codigoTelefone == null ? (short) 1 : (short) (codigoTelefone + 1);
	}

	public ClienteTelefone salvarTelefone(Cliente cliente, ClienteTelefoneInclusaoCommand comando) {
		ClienteTelefone telefone = ClienteTelefone.criar(cliente, comando, getProximoCodigoTelefoneFromCliente(cliente.getCodigo()));
		return telefoneRepository.save(telefone);
	}

	public Page<ClienteTelefone> listarTelefonesFromCliente(Integer codigo, Pageable pageable) {
		Specification<ClienteTelefone> criterio =
				Specification.where(ClienteTelefoneSpecifications.porCodigoCliente(codigo));

		return telefoneRepository.findAll(criterio, pageable);
	}

	private ClienteTelefone getTelefoneById(ClienteTelefoneId clienteTelefoneId) {
		return telefoneRepository.findById(clienteTelefoneId).orElse(null);
	}

	public boolean excluirTelefone(Integer codigoCliente, Short codigoTelefone) {
		ClienteTelefone telefone = getTelefoneById(new ClienteTelefoneId(codigoCliente, codigoTelefone));

		if(telefone == null)
			return false;

		telefoneRepository.delete(telefone);
		return true;
	}


	public ClienteTelefone salvarTelefone(Integer codigoCliente, Short codigoTelefone, ClienteTelefoneEdicaoCommand comando) {
		ClienteTelefone telefone = getTelefoneById(new ClienteTelefoneId(codigoCliente, codigoTelefone));

		if(telefone == null)
			return telefone;

		telefone.editar(comando);
		return telefoneRepository.save(telefone);
	}
    

}
