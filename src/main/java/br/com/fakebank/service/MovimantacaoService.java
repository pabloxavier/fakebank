package br.com.fakebank.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fakebank.repository.MovimentacaoRepository;

public class MovimantacaoService {

	@Autowired
	private MovimentacaoRepository repository;
	
}
