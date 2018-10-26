package br.com.fakebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.MotivoEncerramento;
import br.com.fakebank.domain.TipoConta;
import br.com.fakebank.exceptions.NaoEncontradoException;
import br.com.fakebank.repository.MotivoEncerramentoRepository;
import br.com.fakebank.repository.TipoContaRepository;

@Service
public class MotivoEncerramentoService {

	@Autowired
	MotivoEncerramentoRepository repository;
	
	public MotivoEncerramentoService() {
		
	}
	
	public List<MotivoEncerramento> listar() {
		
		return repository.findAll();
	}
	
	public MotivoEncerramento consultaPorCodigo(Integer codigo) {
		
		return repository.findById(codigo).orElseThrow(() -> new NaoEncontradoException());
	}
	
}
