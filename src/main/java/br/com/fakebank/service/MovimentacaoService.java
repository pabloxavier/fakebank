package br.com.fakebank.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fakebank.domain.Movimentacao;
import br.com.fakebank.domain.commands.MovimentacaoDepositoCommand;
import br.com.fakebank.domain.commands.MovimentacaoSaqueCommand;
import br.com.fakebank.domain.commands.MovimentacaoTransferenciaCommand;
import br.com.fakebank.repository.MovimentacaoRepository;

public class MovimentacaoService {

	@Autowired
	private MovimentacaoRepository repository;
	
	public Movimentacao listar() {
		return null;
	}
	
	public Movimentacao consultarPorCodigo(final Integer codigo) {
		return null;
	}
	
	public Movimentacao filtrar(Integer conta, String tipoMovimentacao, LocalDate dataInicio, LocalDate dataFinal) {
		return null;
	}
	
	public Movimentacao transferir(MovimentacaoTransferenciaCommand comando) {
		return null;
	}
	
	public Movimentacao sacar(MovimentacaoSaqueCommand comando) {
		return null;
	}

	public Movimentacao depositar(MovimentacaoDepositoCommand comando) {
		return null;
	}
}
