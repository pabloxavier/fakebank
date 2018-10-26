package br.com.fakebank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Agencia;
import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.Conta;
import br.com.fakebank.domain.Gerente;
import br.com.fakebank.domain.SituacaoConta;
import br.com.fakebank.domain.TipoConta;
import br.com.fakebank.domain.commands.AgenciaEdicaoCommand;
import br.com.fakebank.domain.commands.ContaCorrenteInclusaoCommand;
import br.com.fakebank.domain.commands.ContaPoupancaInclusaoCommand;
import br.com.fakebank.domain.commands.ContaSalarioInclusaoCommand;
import br.com.fakebank.domain.specifications.ContaSpecifications;
import br.com.fakebank.exceptions.NaoEncontradoException;
import br.com.fakebank.repository.ClienteRepository;
import br.com.fakebank.repository.ContaRepository;
import br.com.fakebank.repository.GerenteRepository;
import br.com.fakebank.repository.SituacaoContaRepository;
import br.com.fakebank.repository.TipoContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private GerenteRepository gerenteRepository;
	@Autowired
	private TipoContaRepository tipoContaRepository;
	@Autowired 
	private SituacaoContaRepository situacaoContaRepository;
	
	
	public List<Conta> listar(){
		return repository.findAll();
	}
	
	public Conta consultarPorCodigo(String codigo) {
		return repository.findById(codigo).orElseThrow(() -> new NaoEncontradoException());
	}
	
	public List<Conta> filtrarPorTipo(Integer tipoConta){
		Specification<Conta> criteria = Specification.where(ContaSpecifications.porTipoConta(tipoConta));
		return repository.findAll(criteria);
	}
	
	public Conta salvarContaCorrente(ContaCorrenteInclusaoCommand command) {
		Conta contaCorrente = Conta.criarContaCorrente(command);
		return repository.save(contaCorrente);
	}
	
	public Conta salvarContaPoupanca(ContaPoupancaInclusaoCommand command) {
		Conta contaPoupanca = Conta.criarContaPoupanca(command);
		return repository.save(contaPoupanca);
	}
	
	public Conta salvarContaSalario(ContaSalarioInclusaoCommand command) {
		Conta contaSalario = Conta.criarContaSalario(command);
		return repository.save(contaSalario);
	}
	
	public boolean excluir(String codigo){
		Conta conta = consultarPorCodigo(codigo);
		
		if (conta == null)
			return false;
		
		repository.deleteById(codigo);
		return true;
	}
	
	public Boolean isClientePrincipalPresent(Integer codigo) {
		Optional<Cliente> cliente = clienteRepository.findById(codigo);
		return cliente.isPresent();
	}
	
	public Boolean isGerentePresent(Integer codigo) {
		Optional<Gerente> gerente = gerenteRepository.findById(codigo);
		return gerente.isPresent();
	}
	
	public Boolean isTipoContaPresent(Integer codigo) {
		Optional<TipoConta> tipoConta = tipoContaRepository.findById(codigo);
		return tipoConta.isPresent();
	}
	
	public Boolean isSituacaoContaPresent(Integer codigo) {
		Optional<SituacaoConta> situacaoConta = situacaoContaRepository.findById(codigo);
		return situacaoConta.isPresent();
	}
	
	
}
