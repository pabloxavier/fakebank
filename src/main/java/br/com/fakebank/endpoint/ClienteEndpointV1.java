package br.com.fakebank.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.commands.AgenciaEdicaoCommand;
import br.com.fakebank.domain.commands.ClienteEdicaoCommand;
import br.com.fakebank.domain.commands.ClienteInclusaoCommand;
import br.com.fakebank.service.ClienteService;

@RestController
@RequestMapping({"v1/clientes", "clientes"})
public class ClienteEndpointV1 extends FakebankEndpoint{

    @Autowired
    ClienteService service;
    
    @GetMapping
    public ResponseEntity<?> listarClientes(){
        return ok(service.listar());
    }
    
    @GetMapping(value = "/{codigo}")
    public ResponseEntity<?> getClienteById(@PathVariable("codigo") Integer codigo){
        return ok(service.getClienteById(codigo));
    }
    
    @GetMapping(path = "/pesquisa")
    public ResponseEntity<?> filtrar(@RequestParam(name = "endereco") String endereco,
                                     @RequestParam(name = "isAtivo") boolean isAtivo){
        return ok(service.filtrar(endereco, isAtivo));
    }
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ClienteInclusaoCommand comando){
        Cliente cliente = service.salvar(comando);
        if (cliente != null)
            return created("cliente incluido com sucesso");
        else
            return notFound("erro ao incluir");
    }
    
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<?> editar(@PathVariable("codigo") Integer codigo, @RequestBody ClienteEdicaoCommand comando){
        Cliente cliente = service.salvar(codigo, comando);
        if (cliente != null)
            return created("editado com sucesso");
        else
            return notFound("agencia nao encontrada");
    }
    
    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<?> excluirCliente(@PathVariable("codigo") Integer codigo){
        return service.excluir(codigo) ? ok("excluido com sucesso") : notFound("cliente nao encontrado");
    }
    
}
