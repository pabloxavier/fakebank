package br.com.fakebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Conta;
import br.com.fakebank.exceptions.NaoEncontradoException;
import br.com.fakebank.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;
	
	public List<Conta> listar(){
		return repository.findAll();
	}
	
	public Conta consultarPorCodigo(Integer codigo) {
		return repository.findById(codigo).orElseThrow(() -> new NaoEncontradoException());
	}
}
