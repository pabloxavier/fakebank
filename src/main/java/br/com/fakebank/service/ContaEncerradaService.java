package br.com.fakebank.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fakebank.domain.ContaEncerrada;
import br.com.fakebank.exceptions.NotFoundException;
import br.com.fakebank.repository.ContaEncerradaRepository;

public class ContaEncerradaService {
    
    @Autowired
    private ContaEncerradaRepository repository;
    
	public ContaEncerrada ContaPorCodigo(String codigo) {
		return repository.findById(codigo).orElseThrow(() -> new NotFoundException());
	}
  
    
}
