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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fakebank.domain.Gerente;
import br.com.fakebank.domain.commands.GerenteEdicaoCommand;
import br.com.fakebank.domain.commands.GerenteInclusaoCommand;
import br.com.fakebank.representations.GerenteRepresentation;
import br.com.fakebank.service.GerenteService;
import br.com.fakebank.util.ListaPaginada;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("gerentes")
public class GerenteEndpoint extends FakebankEndpoint{

    @Autowired
    GerenteService service;
    
    @ApiOperation(
    		value = "Listar todos os gerentes cadastrados.",
    		response = GerenteRepresentation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Gerente retornado com sucesso."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso"),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso"),
            @ApiResponse(code = 404, message = "Nenhuma gerente encontrado")
    })
    @GetMapping
	public ResponseEntity<?> listarGerentes(Pageable pageable){
    	Page<Gerente> gerentes = service.listar(pageable);
    	ListaPaginada<GerenteRepresentation> model = GerenteRepresentation.from(gerentes);
    	return ok(model);
    }
    
    @ApiOperation(
    		value = "Listar todos os gerentes cadastrados.",
    		response = GerenteRepresentation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Gerente retornado com sucesso."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso"),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso"),
            @ApiResponse(code = 404, message = "Nenhuma gerente encontrado")
    })
    @GetMapping(value = "/{codigo}")
    public ResponseEntity<?> getGerenteById(@PathVariable("codigo") Integer codigo){
        return ok(service.getGerenteById(codigo));
    }
    
    @ApiOperation(
    		value = "Listar todos os gerentes cadastrados.",
    		response = GerenteRepresentation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Gerente retornado com sucesso."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso"),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso"),
            @ApiResponse(code = 404, message = "Nenhuma gerente encontrado")
    })
    @GetMapping(path = "/pesquisa")
    public ResponseEntity<?> filtrar(
    		@RequestParam(name = "isAtivo") boolean isAtivo){
        return ok(service.filtrar(isAtivo));
    }
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody GerenteInclusaoCommand comando) {
        Gerente gerente = service.salvar(comando);
        if (gerente != null)
            return created("Gerente criado com sucesso");
        else
            return notFound("Erro ao criar gerente");
    }
    
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<?> editar(@PathVariable("codigo") Integer codigo, @RequestBody GerenteEdicaoCommand comando){
        Gerente gerente = service.salvar(codigo, comando);
        if (gerente != null)
            return created("Dados Gerente salvo com sucesso");
        else
            return notFound("Gerente nÃ£o existe!");
    }
    
    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<?> excluirGerente(@PathVariable("codigo") Integer codigo){
        return service.excluir(codigo) ? ok("Gerente excluÃ­do!") : notFound("Gerente invÃ¡lido!");
    }
    
}
