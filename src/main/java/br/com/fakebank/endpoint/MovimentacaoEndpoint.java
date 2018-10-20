package br.com.fakebank.endpoint;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fakebank.domain.Movimentacao;
import br.com.fakebank.domain.commands.MovimentacaoDepositoCommand;
import br.com.fakebank.domain.commands.MovimentacaoSaqueCommand;
import br.com.fakebank.domain.commands.MovimentacaoTransferenciaCommand;
import br.com.fakebank.representations.MovimentacaoRepresentation;
import br.com.fakebank.service.MovimentacaoService;

@RestController
@RequestMapping("movimentacoes")
public class MovimentacaoEndpoint extends FakebankEndpoint {

	@Autowired
	private MovimentacaoService service;

	@GetMapping
	public ResponseEntity<?> Listar() {
		return ok(service.listar());
	}

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<?> getMovimentacaoById(@PathVariable("codigo") final Integer codigo) {
		Movimentacao Movimentacao = service.consultarPorCodigo(codigo);
		return ok(MovimentacaoRepresentation.from(Movimentacao));
	}

	@GetMapping(path = "/pesquisa")
	public ResponseEntity<?> pesquisarMovimentacao(@RequestParam(value = "conta", required = false) Integer conta,
			@RequestParam(value = "tipoMovimentacao", required = false) String tipoMovimentacao,
			@RequestParam(value = "dataInicio", required = false) LocalDate dataInicio,
			@RequestParam(value = "dataFinal", required = false) LocalDate dataFinal) {

		Movimentacao Movimentacao = service.filtrar(conta, tipoMovimentacao, dataInicio, dataFinal);
		return ok(MovimentacaoRepresentation.from(Movimentacao));
	}

	@PostMapping(value = "/transferencia")
	public ResponseEntity<?> transferir(@RequestBody MovimentacaoTransferenciaCommand comando) {
		return ok(service.transferir(comando));
	}

	@PostMapping(value = "/saque")
	public ResponseEntity<?> sacar(@RequestBody MovimentacaoSaqueCommand comando) {
		return ok(service.sacar(comando));
	}

	@PostMapping(value = "/deposito")
	public ResponseEntity<?> depositar(@RequestBody MovimentacaoDepositoCommand comando) {
		return ok(service.depositar(comando));
	}

}
