package br.com.fakebank.endpoint;

import java.util.List;

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

import br.com.fakebank.domain.Agencia;
import br.com.fakebank.domain.commands.AgenciaEdicaoCommand;
import br.com.fakebank.domain.commands.AgenciaInclusaoCommand;
import br.com.fakebank.representations.AgenciaRepresentationV1;
import br.com.fakebank.service.AgenciaService;

@RestController
@RequestMapping({"v1/agencias", "agencias"})
public class AgenciaEndpointV1 extends FakebankEndpoint{
    
    @Autowired
    private AgenciaService service;

    @GetMapping
    public ResponseEntity<?> listarAgencias(Pageable pageable){
    	Page<Agencia> agencias = service.listar(pageable);
    	List<AgenciaRepresentationV1> model = AgenciaRepresentationV1.from(agencias);
        return ok(agencias); 
    }
    
    @GetMapping(value = "/{codigo}")
    public ResponseEntity<?> getAgenciaById(
    		@PathVariable("codigo") final Integer codigo){
    	
        Agencia agencia = service.consultarPorCodigo(codigo);
        AgenciaRepresentationV1 model = AgenciaRepresentationV1.from(agencia);
        return ok(model); 
    }
    
    @GetMapping(path = "/pesquisa")
    public ResponseEntity<?> pesquisarAgencia(
                @RequestParam(value="numero", required=false) Integer numero, 
                @RequestParam(value="nome", required=false) String nome, 
                @RequestParam(value="cnpj", required=false) String cnpj){
        
    	List<Agencia> agencias = service.filtrar(nome, cnpj, numero);
    	List<AgenciaRepresentationV1> model = AgenciaRepresentationV1.from(agencias);
        return ok(model);
    }
        
    @PostMapping
    public ResponseEntity<?> incluirAgencia(
    		@RequestBody AgenciaInclusaoCommand comando){
    	
        Agencia agenciaIncluida = service.salvar(comando);
        AgenciaRepresentationV1 model = AgenciaRepresentationV1.from(agenciaIncluida);
        return created(model, agenciaIncluida.getCodigo());
    }
    
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<?> editarAgencia(
    		@PathVariable("codigo") Integer codigo,
            @RequestBody AgenciaEdicaoCommand comando){
        
        Agencia agenciaEditada = service.salvar(codigo, comando);
        AgenciaRepresentationV1 model = AgenciaRepresentationV1.from(agenciaEditada);
        return ok(model);
    }
    
    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<?> excluirAgencia(
    		@PathVariable("codigo") Integer codigo){
    	
    	service.excluir(codigo);
        return ok();
    }

}
