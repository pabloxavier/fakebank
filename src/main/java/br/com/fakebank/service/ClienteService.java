package br.com.fakebank.service;

import java.util.List;

import br.com.fakebank.domain.ClienteTelefone;
import br.com.fakebank.domain.ClienteTelefoneId;
import br.com.fakebank.domain.commands.ClienteTelefoneInclusaoCommand;
import br.com.fakebank.domain.specifications.ClienteTelefoneSpecifications;
import br.com.fakebank.repository.ClienteTelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Agencia;
import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.commands.ClienteEdicaoCommand;
import br.com.fakebank.domain.commands.ClienteInclusaoCommand;
import br.com.fakebank.domain.specifications.ClienteSpecifications;
import br.com.fakebank.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private ClienteTelefoneRepository telefoneRepository;
	
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

	private Short getProximoCodigoTelefoneFromCliente(Integer codigoCliente) {
        Short codigoTelefone = telefoneRepository.getUltimoCodigoTelefoneFromCliente(codigoCliente);

        return codigoTelefone == null ? (short) 1 : (short) (codigoTelefone + 1);
    }

	public Object salvarTelefone(Integer codigoCliente, ClienteTelefoneInclusaoCommand comando) {
		ClienteTelefone telefone = ClienteTelefone.criar(codigoCliente, comando, getProximoCodigoTelefoneFromCliente(codigoCliente));
		return telefoneRepository.save(telefone);
	}

	public List<ClienteTelefone> listarTelefonesFromCliente(Integer codigo) {
		Specification<ClienteTelefone> criterio =
				Specification.where(ClienteTelefoneSpecifications.porCodigoCliente(codigo));

		return telefoneRepository.findAll(criterio);
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

}
