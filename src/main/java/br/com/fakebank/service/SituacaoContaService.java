package br.com.fakebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.MotivoEncerramento;
import br.com.fakebank.domain.SituacaoConta;
import br.com.fakebank.domain.TipoConta;
import br.com.fakebank.exceptions.NaoEncontradoException;
import br.com.fakebank.repository.MotivoEncerramentoRepository;
import br.com.fakebank.repository.SituacaoContaRepository;
import br.com.fakebank.repository.TipoContaRepository;

@Service
public class SituacaoContaService {

	@Autowired
	SituacaoContaRepository repository;
	
	public SituacaoContaService() {
		
	}
	
	public List<SituacaoConta> listar() {
		
		return repository.findAll();
	}
	
	public SituacaoConta consultaPorCodigo(Integer codigo) {
		
		return repository.findById(codigo).orElseThrow(() -> new NaoEncontradoException());
	}
	
}
