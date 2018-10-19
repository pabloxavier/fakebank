package br.com.fakebank.endpoint;

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
import br.com.fakebank.domain.commands.AgenciaEdicaoCommand;
import br.com.fakebank.domain.commands.AgenciaInclusaoCommand;
import br.com.fakebank.representations.AgenciaRepresentation;
import br.com.fakebank.service.AgenciaService;

@RestController
@RequestMapping("agencias")
public class AgenciaEndpoint extends FakebankEndpoint{
	
	@Autowired
	private AgenciaService service;

	//@RequestMapping(path = "agencias", method = RequestMethod.GET)
	@GetMapping
	public ResponseEntity<?> listarAgencias(){
		return ok(service.listar()); 
	}
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<?> getAgenciaById(@PathVariable("codigo") final Integer codigo){
		Agencia agencia = service.consultarPorCodigo(codigo);
		return ok(AgenciaRepresentation.from(agencia)); 
	}
	
	@GetMapping(path = "/pesquisa")
	public ResponseEntity<?> pesquisarAgencia(
				@RequestParam(value="numero", required=false) Integer numero, 
				@RequestParam(value="nome", required=false) String nome, 
				@RequestParam(value="cnpj", required=false) String cnpj){
		
		return ok(service.filtrar(nome, cnpj, numero));
	}
		
	@PostMapping
	public ResponseEntity<?> incluirAgencia(@RequestBody @Valid AgenciaInclusaoCommand comando){
		service.salvar(comando);
		return created("incluido com sucesso");
	}
	
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<?> editarAgencia(@PathVariable("codigo") Integer codigo,
										   @RequestBody AgenciaEdicaoCommand comando){
		
		return service.salvar(codigo, comando) != null ? ok("editado com sucesso") : notFound("agencia nao encontrada");
	}
	
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> excluirAgencia(@PathVariable("codigo") Integer codigo){
		return service.excluir(codigo) ? ok("excluida com sucesso") : notFound("agencia nao encontrada");		
	}
		
}