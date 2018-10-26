package br.com.fakebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Conta;
import br.com.fakebank.domain.commands.ContaCorrenteInclusaoCommand;
import br.com.fakebank.domain.commands.ContaPoupancaInclusaoCommand;
import br.com.fakebank.domain.commands.ContaSalarioInclusaoCommand;
import br.com.fakebank.domain.specifications.ContaSpecifications;
import br.com.fakebank.exceptions.NaoEncontradoException;
import br.com.fakebank.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;
	
	public List<Conta> listar(){
		return repository.findAll();
	}
	
	public Conta consultarPorCodigo(String codigo) {
		Specification<Conta> criteria = Specification.where(ContaSpecifications.porCodigo(codigo));
		return repository.findOne(criteria).orElseThrow(() -> new NaoEncontradoException());
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
	
	
}
