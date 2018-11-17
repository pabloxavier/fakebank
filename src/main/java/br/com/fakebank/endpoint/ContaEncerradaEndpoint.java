package br.com.fakebank.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fakebank.domain.ContaEncerrada;
import br.com.fakebank.domain.commands.ContaEncerradaCommand;
import br.com.fakebank.representations.ContaEncerradaRepresentation;
import br.com.fakebank.service.ContaEncerradaService;

@RestController
@RequestMapping("contas")
public class ContaEncerradaEndpoint extends FakebankEndpoint {

	@Autowired
	private ContaEncerradaService service;
	
	@PostMapping(path = "{codigoConta}/encerramento")
	public ResponseEntity<?> encerrarConta(
			@PathVariable(name = "codigoConta") String codigoConta,
			@RequestBody ContaEncerradaCommand comando) {
		
		ContaEncerrada contaEncerrada = service.encerrar(codigoConta, comando);
		ContaEncerradaRepresentation model = ContaEncerradaRepresentation.from(contaEncerrada);
		
		return ok(model);
	}
}
