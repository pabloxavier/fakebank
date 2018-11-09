package br.com.fakebank.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fakebank.domain.MotivoEncerramento;
import br.com.fakebank.domain.commands.DominioCriacaoCommand;
import br.com.fakebank.domain.commands.DominioEdicaoCommand;
import br.com.fakebank.service.MotivoEncerramentoService;

@RestController
@RequestMapping("motivo-encerramento")
public class MotivoEncerramentoEndpoint extends FakebankEndpoint {

	@Autowired
	MotivoEncerramentoService service;

	@GetMapping
	public ResponseEntity<?> listarTipoConta() {

		return ok(service.listar());
	}

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<?> cosultaTipoContaPorCodigo(@PathVariable("codigo") Integer codigo) {
		MotivoEncerramento motivo = service.consultaPorCodigo(codigo);
		return ok(motivo);
	}

	@PostMapping
	public ResponseEntity<?> incluirMotivoEncerramento(@RequestBody DominioCriacaoCommand comando) {
		service.incluir(comando);
		return new ResponseEntity<>("incluido com sucesso", HttpStatus.CREATED);
	}

	@PutMapping(path = "/{codigo}")
	public ResponseEntity<?> editarMotivoEncerramento(@PathVariable("codigo") Integer codigo,
			@RequestBody DominioEdicaoCommand comando) {
		service.editar(comando, codigo);
		return new ResponseEntity<>("Iditado com sucesso", HttpStatus.OK);
	}
}
