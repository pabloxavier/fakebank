package br.com.fakebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.commands.ClienteEdicaoCommand;
import br.com.fakebank.domain.commands.ClienteInclusaoCommand;
import br.com.fakebank.domain.specifications.ClienteSpecifications;
import br.com.fakebank.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> listar(){
		return repository.findAll();
	}

	public Cliente getClienteById(Integer codigo){
		return repository.findById(codigo).orElse(null);
	}

	public List<Cliente> filtrar(String endereco, boolean isAtivo){
		Specification<Cliente> criterio = Specification.where(ClienteSpecifications.porParteEndereco(endereco)
				                                       .and(ClienteSpecifications.porSituacao(isAtivo)));
		return repository.findAll(criterio);
	}

	public Cliente salvar(ClienteInclusaoCommand comando){
		Cliente cliente = Cliente.criar(comando);
		return repository.save(cliente);
	}

	public Cliente salvar(Integer codigo, ClienteEdicaoCommand comando){
		Cliente cliente = getClienteById(codigo);

		if (cliente == null)
			return cliente;


		cliente.editar(comando);
		return repository.save(cliente);
	}

	public boolean excluir(Integer codigo){
		Cliente cliente = getClienteById(codigo);

		if (cliente == null)
			return false;

		repository.deleteById(codigo);
		return true;
	}
}
