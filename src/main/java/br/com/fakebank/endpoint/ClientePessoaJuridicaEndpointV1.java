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

import br.com.fakebank.common.util.ListaPaginada;
import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.TipoPessoa;
import br.com.fakebank.domain.commands.ClientePessoaJuridicaEdicaoCommand;
import br.com.fakebank.domain.commands.ClientePessoaJuridicaInclusaoCommand;
import br.com.fakebank.representations.ClienteRepresentationV1;
import br.com.fakebank.service.ClientePessoaJuridicaService;
import br.com.fakebank.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping({"v1/clientes-pessoa-juridica", "clientes-pessoa-juridica"})
@Api(value = "ClientePessoaJuridicaEndpointV1", description = "Endpoint de operações de Clientes Pessoa Juridica(V1)")
public class ClientePessoaJuridicaEndpointV1 extends FakebankEndpoint{

    @Autowired
    private ClienteService service;
    
    @Autowired
    private ClientePessoaJuridicaService servicePJ;
    
    @ApiOperation(
            value = "Listar todos os clientes PJ cadastrados.",
            response = ClienteRepresentationV1.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes PJ retornadas com sucesso."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso."),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso."),
            @ApiResponse(code = 404, message = "Nenhum cliente PJ encontrado.")
    })
    @GetMapping
    public ResponseEntity<?> listarClientes(Pageable pageable){
    	Page<Cliente> clientes = service.listar(pageable, TipoPessoa.JURIDICA);
        ListaPaginada<ClienteRepresentationV1> model = ClienteRepresentationV1.from(clientes);
        return ok(model);        
    }
    
    @ApiOperation(
    		value = "Consultar um único cliente PJ por código.",
    		response = ClienteRepresentationV1.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente PJ retornado com sucesso."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso"),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso"),
            @ApiResponse(code = 404, message = "Nenhuma cliente PJ encontrado")
    })
    @GetMapping(value = "/{codigo}")
    public ResponseEntity<?> getClienteById(@PathVariable("codigo") Integer codigo){
        Cliente cliente = service.getClienteById(codigo);
        
        ClienteRepresentationV1 model = ClienteRepresentationV1.from(cliente);
        return ok(model);        
    }
       
    @GetMapping(path = "/pesquisa")
    public ResponseEntity<?> filtrar(@RequestParam(name = "endereco") String endereco,
                                     @RequestParam(name = "isAtivo") boolean isAtivo){
        return ok(service.filtrar(endereco, isAtivo));
    }

    @ApiOperation(
    		value = "Incluir um novo cliente PJ.",
    		response = ClientePessoaJuridicaInclusaoCommand.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente PJ criado com sucesso."),
            @ApiResponse(code = 400, message = "Inclusão não permitida por validações.")
    })
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ClientePessoaJuridicaInclusaoCommand comando){
        Cliente cliente = servicePJ.salvar(comando);

        ClienteRepresentationV1 model = ClienteRepresentationV1.from(cliente);
        return created(model, cliente.getCodigo());
    }
    
    @ApiOperation(
    		value = "Alterar informações de um cliente PJ.",
    		response = ClientePessoaJuridicaEdicaoCommand.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente PJ alterado com sucesso."),
            @ApiResponse(code = 400, message = "Alteração não permitida por validações."),
            @ApiResponse(code = 404, message = "Cliente PJ não encontrado")
    })
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<?> editar(
    		@PathVariable("codigo") Integer codigo, 
    		@RequestBody ClientePessoaJuridicaEdicaoCommand comando){
        Cliente cliente = servicePJ.salvar(codigo, comando);
        
        ClienteRepresentationV1 model = ClienteRepresentationV1.from(cliente);
        return ok(model);
    }
      
    @ApiOperation(
    		value = "Excluir um cliente PJ por código.",
    		response = ClienteRepresentationV1.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente PJ removido com sucesso."),
            @ApiResponse(code = 400, message = "Exclusão não permitida por validações."),
            @ApiResponse(code = 404, message = "Cliente PJ não encontrado")
    })
    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<?> excluirCliente(
    		@PathVariable("codigo") Integer codigo){
    	
        service.excluir(codigo);
        return ok();
    }
    
}