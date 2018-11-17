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
import br.com.fakebank.domain.commands.ClientePessoaFisicaEdicaoCommand;
import br.com.fakebank.domain.commands.ClientePessoaFisicaInclusaoCommand;
import br.com.fakebank.domain.commands.ClientePessoaJuridicaEdicaoCommand;
import br.com.fakebank.domain.commands.ClientePessoaJuridicaInclusaoCommand;
import br.com.fakebank.representations.AgenciaRepresentationV1;
import br.com.fakebank.representations.ClienteRepresentationV1;
import br.com.fakebank.service.ClientePessoaFisicaService;
import br.com.fakebank.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping({"v1/clientes-pessoa-fisica", "clientes-pessoa-fisica"})
@Api(value = "ClientePessoaFisicaEndpointV1", description = "Endpoint de operações de Clientes Pessoa Fisica(V1)")
public class ClientePessoaFisicaEndpointV1 extends FakebankEndpoint{

   @Autowired
   private ClienteService service;
   
   @Autowired
   private ClientePessoaFisicaService servicePF;

   @ApiOperation(
           value = "Listar todos os clientes PF cadastrados.",
           response = ClienteRepresentationV1.class)
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Clientes PF retornadas com sucesso."),
           @ApiResponse(code = 401, message = "Recurso sem autorização de acesso."),
           @ApiResponse(code = 403, message = "Acesso negado ao recurso."),
           @ApiResponse(code = 404, message = "Nenhum cliente PF encontrada.")
   })
   @GetMapping
   public ResponseEntity<?> listarClientes(Pageable pageable){
       Page<Cliente> clientes = service.listar(pageable, TipoPessoa.FISICA);
       ListaPaginada<ClienteRepresentationV1> model = ClienteRepresentationV1.from(clientes);
       return ok(model);
   }

   @ApiOperation(
   		value = "Consultar um único cliente PF por código.",
   		response = ClienteRepresentationV1.class)
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Cliente PF retornado com sucesso."),
           @ApiResponse(code = 401, message = "Recurso sem autorização de acesso"),
           @ApiResponse(code = 403, message = "Acesso negado ao recurso"),
           @ApiResponse(code = 404, message = "Nenhuma cliente PF encontrado")
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
   		value = "Incluir um novo cliente PF.",
   		response = ClientePessoaFisicaInclusaoCommand.class)
   @ApiResponses(value = {
           @ApiResponse(code = 201, message = "Cliente PF criado com sucesso."),
           @ApiResponse(code = 400, message = "Inclusão não permitida por validações.")
   })
   @PostMapping
   public ResponseEntity<?> criar(@RequestBody ClientePessoaFisicaInclusaoCommand comando){
       Cliente cliente = servicePF.salvar(comando);

       ClienteRepresentationV1 model = ClienteRepresentationV1.from(cliente);
       return created(model, cliente.getCodigo());
   }

   @ApiOperation(
   		value = "Alterar informações de um cliente PF.",
   		response = ClientePessoaFisicaEdicaoCommand.class)
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Cliente PF alterado com sucesso."),
           @ApiResponse(code = 400, message = "Alteração não permitida por validações."),
           @ApiResponse(code = 404, message = "Cliente PF não encontrado")
   })
   @PutMapping(value = "/{codigo}")
   public ResponseEntity<?> editar(@PathVariable("codigo") Integer codigo, @RequestBody ClientePessoaFisicaEdicaoCommand comando){
       Cliente cliente = servicePF.salvar(codigo, comando);
       
       ClienteRepresentationV1 model = ClienteRepresentationV1.from(cliente);
       return ok(model);
   }

   @ApiOperation(
   		value = "Excluir um cliente PF por código.",
   		response = ClienteRepresentationV1.class)
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Cliente PF removido com sucesso."),
           @ApiResponse(code = 400, message = "Exclusão não permitida por validações."),
           @ApiResponse(code = 404, message = "Cliente PF não encontrado")
   })
   @DeleteMapping(value = "/{codigo}")
   public ResponseEntity<?> excluirCliente(@PathVariable("codigo") Integer codigo){
       service.excluir(codigo);
       return ok();
   }
   
}