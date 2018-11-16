package br.com.fakebank.endpoint;

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

import br.com.fakebank.domain.Gerente;
import br.com.fakebank.domain.commands.GerenteEdicaoCommand;
import br.com.fakebank.domain.commands.GerenteInclusaoCommand;
import br.com.fakebank.service.GerenteService;

@RestController
@RequestMapping("gerentes")
public class GerenteEndpoint extends FakebankEndpoint{

    @Autowired
    GerenteService service;
//    
//    @GetMapping
//	public ResponseEntity<?> listarGerentes(Pageable pageable){
//    	Page<Gerente> gerentes = service.listar(pageable);
//    	ListaPaginada<GerenteRepresentation> model = GerenteRepresentation.from(gerentes);
//    	return ok(model);
//    }
    
    @GetMapping(value = "/{codigo}")
    public ResponseEntity<?> getGerenteById(@PathVariable("codigo") Integer codigo){
        return ok(service.getGerenteById(codigo));
    }
    
    @GetMapping(path = "/pesquisa")
    public ResponseEntity<?> filtrar(
    		@RequestParam(name = "isAtivo") boolean isAtivo){
        return ok(service.filtrar(isAtivo));
    }
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody GerenteInclusaoCommand comando){
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
            return notFound("Gerente não existe!");
    }
    
    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<?> excluirGerente(@PathVariable("codigo") Integer codigo){
        return service.excluir(codigo) ? ok("Gerente excluído!") : notFound("Gerente inválido!");
    }
    
}
