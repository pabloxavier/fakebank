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

import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.TipoPessoa;
import br.com.fakebank.domain.commands.ClientePessoaJuridicaEdicaoCommand;
import br.com.fakebank.domain.commands.ClientePessoaJuridicaInclusaoCommand;
import br.com.fakebank.representations.ClienteRepresentationV1;
import br.com.fakebank.service.ClientePessoaFisicaService;
import br.com.fakebank.service.ClientePessoaJuridicaService;
import br.com.fakebank.service.ClienteService;
import br.com.fakebank.util.ListaPaginada;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping({"v1/clientes-pessoa-juridica", "clientes-pessoa-juridica"})
@Api(value = "ClientePessoaJuridicaEndpointV1", description = "Endpoint de operações de Clientes Pessoa Juridica(V1)")
public class ClientePessoaJuridicaEndpointV1 extends FakebankEndpoint{

    @Autowired
    ClienteService service;
    
    @Autowired
    ClientePessoaJuridicaService servicePJ;
    
    @ApiOperation(
            value = "Listar todos os clientes PJ cadastrados.",
            response = ClienteRepresentationV1.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes PJ retornadas com sucesso."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso."),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso."),
            @ApiResponse(code = 404, message = "Nenhum cliente PJ encontrada.")
    })
    @GetMapping
    public ResponseEntity<?> listarClientes(Pageable pageable){
    	Page<Cliente> clientes = service.listar(pageable, TipoPessoa.JURIDICA);
        ListaPaginada<ClienteRepresentationV1> model = ClienteRepresentationV1.from(clientes);
        return ok(model);        
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
            return created("Cliente PJ incluido com sucesso");
        else
            return notFound("erro ao incluir cliente PJ");
    }
    
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<?> editar(@PathVariable("codigo") Integer codigo, @RequestBody ClientePessoaJuridicaEdicaoCommand comando){
        Cliente cliente = servicePJ.salvar(codigo, comando);
        if (cliente != null)
            return created("Cliente PJ editado com sucesso");
        else
            return notFound("Cliente PJ não encontrado");
    }
      
    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<?> excluirCliente(@PathVariable("codigo") Integer codigo){
        return service.excluir(codigo) ? ok("Cliente PJ excluído com sucesso") : notFound("Cliente PJ não encontrado");
    }
    
}