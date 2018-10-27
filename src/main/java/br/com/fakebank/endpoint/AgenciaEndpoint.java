package br.com.fakebank.endpoint;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<?> listarAgencias(){
    	List<Agencia> agencias = service.listar();
    	List<AgenciaRepresentation> model = AgenciaRepresentation.from(agencias);
        return ok(model); 
    }
    
    @GetMapping(value = "/{codigo}")
    public ResponseEntity<?> getAgenciaById(
    		@PathVariable("codigo") final Integer codigo){
    	
        Agencia agencia = service.consultarPorCodigo(codigo);
        AgenciaRepresentation model = AgenciaRepresentation.from(agencia);
        return ok(model); 
    }
    
    @GetMapping(path = "/pesquisa")
    public ResponseEntity<?> pesquisarAgencia(
                @RequestParam(value="numero", required=false) Integer numero, 
                @RequestParam(value="nome", required=false) String nome, 
                @RequestParam(value="cnpj", required=false) String cnpj){
        
    	List<Agencia> agencias = service.filtrar(nome, cnpj, numero);
    	List<AgenciaRepresentation> model = AgenciaRepresentation.from(agencias);
        return ok(model);
    }
        
    @PostMapping
    public ResponseEntity<?> incluirAgencia(
    		@RequestBody AgenciaInclusaoCommand comando){
    	
        Agencia agenciaIncluida = service.salvar(comando);
        AgenciaRepresentation model = AgenciaRepresentation.from(agenciaIncluida);
        return created(model, agenciaIncluida.getCodigo());
    }
    
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<?> editarAgencia(
    		@PathVariable("codigo") Integer codigo,
            @RequestBody AgenciaEdicaoCommand comando){
        
        Agencia agenciaEditada = service.salvar(codigo, comando);
        AgenciaRepresentation model = AgenciaRepresentation.from(agenciaEditada);
        return ok(model);
    }
    
    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<?> excluirAgencia(
    		@PathVariable("codigo") Integer codigo){
    	
    	service.excluir(codigo);
        return ok();
    }

}
