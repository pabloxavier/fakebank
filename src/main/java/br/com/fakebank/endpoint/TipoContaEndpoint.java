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

import br.com.fakebank.domain.TipoConta;
import br.com.fakebank.domain.commands.DominioCriacaoCommand;
import br.com.fakebank.domain.commands.DominioEdicaoCommand;
import br.com.fakebank.representations.TipoContaRepresentation;
import br.com.fakebank.service.TipoContaService;
import br.com.fakebank.util.ListaPaginada;

@RestController
@RequestMapping("tipos-conta")
public class TipoContaEndpoint extends FakebankEndpoint{

    @Autowired
    TipoContaService service;
    
    @GetMapping
    public ResponseEntity<?> listarTipoConta(Pageable pageable){
    	Page<TipoConta> tiposConta = service.listar(pageable);
    	ListaPaginada<TipoContaRepresentation> model = TipoContaRepresentation.from(tiposConta);
        return ok(model); 
    }
    
    @GetMapping(value="/{codigo}")
    public ResponseEntity<?> cosultaTipoContaPorCodigo(@PathVariable("codigo") Integer codigo ){
        TipoConta tipo = service.consultaPorCodigo(codigo);
        TipoContaRepresentation model = TipoContaRepresentation.from(tipo);
        return ok(model);
    }
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody DominioCriacaoCommand comando){
        TipoConta tipoConta = service.salvar(comando);
       	TipoContaRepresentation model = TipoContaRepresentation.from(tipoConta);
       	return ok(model);
    }
    
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<?> editar(@PathVariable("codigo") Integer codigo, @RequestBody DominioEdicaoCommand comando){
        TipoConta tipoConta = service.salvar(codigo, comando);
        
        if (tipoConta == null)
        	return notFound("tipo conta nao encontrado");
        
        TipoContaRepresentation model = TipoContaRepresentation.from(tipoConta);
        return created(model);
    }
    
    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<?> excluir(@PathVariable("codigo") Integer codigo){
    	service.excluir(codigo);
        return ok("excluido com sucesso");
    }

}
