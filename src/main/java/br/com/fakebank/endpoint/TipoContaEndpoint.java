package br.com.fakebank.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fakebank.domain.TipoConta;
import br.com.fakebank.domain.commands.ClienteEdicaoCommand;
import br.com.fakebank.domain.commands.DominioCriacaoCommand;
import br.com.fakebank.domain.commands.DominioEdicaoCommand;
import br.com.fakebank.service.TipoContaService;

@RestController
@RequestMapping("tipo-conta")
public class TipoContaEndpoint extends FakebankEndpoint{

	@Autowired
	TipoContaService service;
	
	@GetMapping
	public ResponseEntity<?> listarTipoConta(){
		
		return ok(service.listar());
	}
	
	@GetMapping(value="/{codigo}")
	public ResponseEntity<?> cosultaTipoContaPorCodigo(@PathVariable("codigo") Integer codigo ){
		TipoConta tipo = service.consultaPorCodigo(codigo);
		return ok(tipo);
	}
	
	@PostMapping
	public ResponseEntity<?> criar(@RequestBody DominioCriacaoCommand comando){
		TipoConta tipoConta= service.salvar(comando);
		if (tipoConta != null)
			return created("tipo conta criado com sucesso");
		else
			return notFound("tipo conta nao encontrado");
	}
	
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<?> editar(@PathVariable("codigo") Integer codigo, @RequestBody DominioEdicaoCommand comando){
		TipoConta tipoConta = service.salvar(codigo, comando);
		
		if (tipoConta != null)
			return created("tipo conta editado com sucesso");
		else
			return notFound("tipo conta nao encontrado");
			
	}
}
