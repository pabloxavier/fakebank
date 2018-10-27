package br.com.fakebank.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fakebank.service.PessoaService;

@RestController
@RequestMapping("pessoas")
public class PessoaEndpoint extends FakebankEndpoint{

    @Autowired
    PessoaService service;
    
    @GetMapping
    public ResponseEntity<?> listarPessoas(){
        return ok(service.listar());
    }
    
    @GetMapping(value = "/{codigo}")
    public ResponseEntity<?> getPessoaById(@PathVariable("codigo") Integer codigo){
        return ok(service.getPessoaById(codigo));
    }
    
    @GetMapping(path = "/pesquisa")
    public ResponseEntity<?> filtrarPessoa(@RequestParam(name = "documento", required = false) String documento, 
                                           @RequestParam(name = "tipoPessoa", required = false) String tipo, 
                                           @RequestParam(name = "nome", required = false) String nome){
        return ok(service.filtrar(documento, tipo, nome));
    }
}
