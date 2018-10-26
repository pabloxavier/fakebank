package br.com.fakebank.endpoint;

import br.com.fakebank.domain.commands.AgenciaEdicaoCommand;
import br.com.fakebank.domain.commands.ClienteTelefoneInclusaoCommand;
import br.com.fakebank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("clientes")
public class ClienteTelefoneEndpoint extends FakebankEndpoint{

	@Autowired
	ClienteService service;

	@GetMapping(value = "/{codigoCliente}/telefones")
	public ResponseEntity<?> getTelefonesByClienteId(@PathVariable("codigoCliente") Integer codigoCliente){
		return ok("ok");
	}

	@PostMapping(value = "/{codigoCliente}/telefones")
	public ResponseEntity<?> incluirTelefone(
			@PathVariable("codigoCliente") Integer codigoCliente,
			@Valid @RequestBody ClienteTelefoneInclusaoCommand comando){
		return ok(service.salvarTelefone(codigoCliente, comando));
	}

	@DeleteMapping(value = "/{codigoCliente}/telefones/{codigoTelefone}")
	public ResponseEntity<?> excluirTelefone(
			@PathVariable("codigoCliente") Integer codigoCliente,
			@PathVariable("codigoTelefone") Integer codigoTelefone){
		return ok("ok");
	}
	
}
