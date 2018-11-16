package br.com.fakebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fakebank.domain.Conta;
import br.com.fakebank.exceptions.NotFoundException;
import br.com.fakebank.repository.ContaRepository;

@Service
public class ContaEncerradaService {
    
    @Autowired
    private ContaRepository repository;
    
	public Conta ContaPorCodigo(String codigo) {
		return repository.findById(codigo).orElseThrow(() -> new NotFoundException());
	}
  
    
}
