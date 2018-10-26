package br.com.fakebank.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fakebank.domain.MotivoEncerramento;
import br.com.fakebank.domain.TipoConta;
import br.com.fakebank.service.MotivoEncerramentoService;
import br.com.fakebank.service.TipoContaService;

@RestController
@RequestMapping("motivo-encerramento")
public class MotivoEncerramentoEndpoint extends FakebankEndpoint{

    @Autowired
    MotivoEncerramentoService service;
    
    @GetMapping
    public ResponseEntity<?> listarTipoConta(){
        
        return ok(service.listar());
    }
    
    @GetMapping(value="/{codigo}")
    public ResponseEntity<?> cosultaTipoContaPorCodigo(@PathVariable("codigo") Integer codigo ){
        MotivoEncerramento motivo = service.consultaPorCodigo(codigo);
        return ok(motivo);
    }
}
