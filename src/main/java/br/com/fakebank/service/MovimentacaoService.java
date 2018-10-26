package br.com.fakebank.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.fakebank.domain.Movimentacao;
import br.com.fakebank.domain.commands.MovimentacaoDepositoCommand;
import br.com.fakebank.domain.commands.MovimentacaoSaqueCommand;
import br.com.fakebank.domain.commands.MovimentacaoTransferenciaCommand;

public class MovimentacaoService {

	public List<Movimentacao> listar() {
		//TODO
		return null;
	}

	public Movimentacao consultarPorCodigo(Integer codigo) {
		//TODO
		return null;
	}

	public Movimentacao filtrar(Integer conta, LocalDate dataInicio, LocalDate dataFinal) {
		//TODO
		return null;
	}

	public ResponseEntity<?> transferir(MovimentacaoTransferenciaCommand comando) {
		//TODO
		return null;
	}

	public ResponseEntity<?> sacar(MovimentacaoSaqueCommand comando) {
		//TODO
		return null;
	}

	public ResponseEntity<?> depositar(MovimentacaoDepositoCommand comando) {
		//TODO
		return null;
	}

}
