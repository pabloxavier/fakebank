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

import br.com.fakebank.common.util.ListaPaginada;
import br.com.fakebank.domain.SituacaoConta;
import br.com.fakebank.domain.commands.DominioCriacaoCommand;
import br.com.fakebank.domain.commands.DominioEdicaoCommand;
import br.com.fakebank.representations.SituacaoContaRepresentation;
import br.com.fakebank.service.SituacaoContaService;

@RestController
@RequestMapping("situacoes-conta")
public class SituacaoContaEndpoint extends FakebankEndpoint{

    @Autowired
    SituacaoContaService service;
    
    @GetMapping
    public ResponseEntity<?> listarTipoConta(Pageable pageable){
    	Page<SituacaoConta> situacoesConta = service.listar(pageable);
    	ListaPaginada<SituacaoContaRepresentation> model = SituacaoContaRepresentation.from(situacoesConta);
        return ok(model); 
    }
    
    @GetMapping(value="/{codigo}")
    public ResponseEntity<?> cosultaTipoContaPorCodigo(@PathVariable("codigo") Integer codigo ){
        SituacaoConta situacao = service.consultaPorCodigo(codigo);
        SituacaoContaRepresentation model = SituacaoContaRepresentation.from(situacao);
        return ok(model);
    }
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody DominioCriacaoCommand comando){
        SituacaoConta situacaoConta= service.salvar(comando);
        SituacaoContaRepresentation model = SituacaoContaRepresentation.from(situacaoConta);
       	return ok(model);
    }
    
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<?> editar(@PathVariable("codigo") Integer codigo, @RequestBody DominioEdicaoCommand comando){
        SituacaoConta situacaoConta = service.salvar(codigo, comando);
        
        if (situacaoConta == null)
        	 return notFound("situacao conta nao encontrada");
        
       	SituacaoContaRepresentation model = SituacaoContaRepresentation.from(situacaoConta);
        return created(model);
           
    }
    
    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<?> excluir(@PathVariable("codigo") Integer codigo){
    	service.excluir(codigo);
        return ok("excluido com sucesso");
    }

}
