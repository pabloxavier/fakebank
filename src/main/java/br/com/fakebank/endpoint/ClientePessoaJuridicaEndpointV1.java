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

import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.TipoPessoa;
import br.com.fakebank.domain.commands.ClientePessoaJuridicaEdicaoCommand;
import br.com.fakebank.domain.commands.ClientePessoaJuridicaInclusaoCommand;
import br.com.fakebank.service.ClientePessoaFisicaService;
import br.com.fakebank.service.ClientePessoaJuridicaService;
import br.com.fakebank.service.ClienteService;

@RestController
@RequestMapping({"v1/clientes-pessoa-juridica", "clientes-pessoa-juridica"})
public class ClientePessoaJuridicaEndpointV1 extends FakebankEndpoint{

    @Autowired
    ClienteService service;
    
    @Autowired
    ClientePessoaJuridicaService servicePJ;
    
    @GetMapping
    public ResponseEntity<?> listarClientes(){
        //return ok(service.listar());
    	return ok(servicePJ.listar(TipoPessoa.JURIDICA));
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
    public ResponseEntity<?> criar(@RequestBody ClientePessoaJuridicaInclusaoCommand comando){
        Cliente cliente = servicePJ.salvar(comando);
        if (cliente != null)
            return created("Cliente incluido com sucesso");
        else
            return notFound("erro ao incluir cliente");
    }
    
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<?> editar(@PathVariable("codigo") Integer codigo, @RequestBody ClientePessoaJuridicaEdicaoCommand comando){
        Cliente cliente = servicePJ.salvar(codigo, comando);
        if (cliente != null)
            return created("editado com sucesso");
        else
            return notFound("Cliente não encontrado");
    }
    
  
    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<?> excluirCliente(@PathVariable("codigo") Integer codigo){
        return service.excluir(codigo) ? ok("Excluído com sucesso") : notFound("Cliente não encontrado");
    }
    
}
