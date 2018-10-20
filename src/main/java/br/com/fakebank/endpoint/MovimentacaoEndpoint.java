package br.com.fakebank.endpoint;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fakebank.domain.Agencia;
import br.com.fakebank.domain.Movimentacao;
import br.com.fakebank.domain.commands.AgenciaEdicaoCommand;
import br.com.fakebank.domain.commands.MovimentacaoEdicaoCommand;
import br.com.fakebank.domain.commands.MovimentacaoInclusaoCommand;
import br.com.fakebank.representations.AgenciaRepresentation;
import br.com.fakebank.representations.MovimentacaoRepresentation;
import br.com.fakebank.service.MovimentacaoService;

@RestController
@RequestMapping("movimentacoes")
public class MovimentacaoEndpoint extends FakebankEndpoint {

	@Autowired
//	private MovimentacaoService service;

//	consultar-movimentacoes
//	consultar-movimentacao-por-codigo
//
//	consultar-movimentacao-por-conta
//	consultar-movimentacao-por-data

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
	public ResponseEntity<?> pesquisarMovimentacao(
				@RequestParam(value="codigo", required=false) Integer codigo, 
				@RequestParam(value="conta", required=false) Integer conta, 
				@RequestParam(value="dataInicio", required=false) LocalDate dataInicio,
				@RequestParam(value="dataFinal", required=false) LocalDate dataFinal) {

		Movimentacao Movimentacao = service.filtrar(codigo, conta, dataInicio, dataFinal);
		return ok(MovimentacaoRepresentation.from(Movimentacao)); 
	}

	@PutMapping(value = "/saque")
	public ResponseEntity<?> sacar(@RequestBody MovimentacaoSaqueCommand comando) {
		return service.sacar(comando);
	}
	
	@PutMapping(value = "/deposito")
	public ResponseEntity<?> depositar(@RequestBody MovimentacaoDepositoCommand comando) {
		return service.depositar(comando);
	}
	
	
		@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> excluirMovimentacao(@PathVariable("codigo") Integer codigo) {
		return service.excluir(codigo) ? ok("excluida com sucesso") : notFound("Movimentacao nao encontrada");
	}
}
