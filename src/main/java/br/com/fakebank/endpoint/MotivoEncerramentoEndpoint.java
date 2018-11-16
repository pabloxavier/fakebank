package br.com.fakebank.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import br.com.fakebank.representations.MotivoEncerramentoRepresentationV1;
import br.com.fakebank.service.MotivoEncerramentoService;
import br.com.fakebank.util.ListaPaginada;

@RestController
@RequestMapping("motivos-encerramento")
public class MotivoEncerramentoEndpoint extends FakebankEndpoint {

	@Autowired
	MotivoEncerramentoService service;

	@GetMapping
	public ResponseEntity<?> listaMotivoEncerramento(Pageable pageable) {
		Page<MotivoEncerramento> motivo = service.listar(pageable);
		ListaPaginada<MotivoEncerramentoRepresentationV1> model = MotivoEncerramentoRepresentationV1.from(motivo);
		return ok(model);
	}

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<?> cosultaMotivoEncerramentoPorCodigo(@PathVariable("codigo") Integer codigo) {
		MotivoEncerramento motivo = service.consultaPorCodigo(codigo);
		MotivoEncerramentoRepresentationV1 model = MotivoEncerramentoRepresentationV1.from(motivo);
		return ok(model);
	}

	@PostMapping
	public ResponseEntity<?> incluirMotivoEncerramento(@RequestBody DominioCriacaoCommand comando) {
		MotivoEncerramento motivoEncerramento = service.incluir(comando);
		MotivoEncerramentoRepresentationV1 model = MotivoEncerramentoRepresentationV1.from(motivoEncerramento);
       	return ok(model);
	}

	@PutMapping(path = "/{codigo}")
	public ResponseEntity<?> editarMotivoEncerramento(@PathVariable("codigo") Integer codigo,
			@RequestBody DominioEdicaoCommand comando) {
		MotivoEncerramento motivoEncerramento = service.editar(comando, codigo);
		
		if(motivoEncerramento == null)
			return notFound("Motivo Encerramento não encontrado");
        
		MotivoEncerramentoRepresentationV1 model = MotivoEncerramentoRepresentationV1.from(motivoEncerramento);
        return created(model);
	}

	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> excluirMotivoEncerramento(@PathVariable("codigo") Integer codigo) {
		service.excluirMotivoEncerramento(codigo);

		return ok("Excluido com sucesso.");
	}

}
