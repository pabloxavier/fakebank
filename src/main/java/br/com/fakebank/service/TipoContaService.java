package br.com.fakebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.TipoConta;
import br.com.fakebank.exceptions.NaoEncontradoException;
import br.com.fakebank.repository.TipoContaRepository;

@Service
public class TipoContaService {

	@Autowired
	TipoContaRepository repository;
	
	public TipoContaService() {
		
	}
	
	public List<TipoConta> listar() {
		
		return repository.findAll();
	}
	
	public TipoConta consultaPorCodigo(Integer codigo) {
		
		return repository.findById(codigo).orElseThrow(() -> new NaoEncontradoException());
	}
	
}
