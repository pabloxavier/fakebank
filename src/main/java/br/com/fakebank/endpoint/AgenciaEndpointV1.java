package br.com.fakebank.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
import br.com.fakebank.util.ListaPaginada;

@RestController
@RequestMapping({"v1/agencias", "agencias"})
@Api(value = "AgenciaEndpointV1", description = "Endpoint de operações de agências (V1)")
public class AgenciaEndpointV1 extends FakebankEndpoint{
    
    @Autowired
    private AgenciaService service;

    @ApiOperation(
    		value = "Listar todas as agências cadastradas.",
    		response = AgenciaRepresentationV1.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agências retornadas com sucesso."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso"),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso"),
            @ApiResponse(code = 404, message = "Nenhuma agência encontrada")
    })
    @GetMapping
    public ResponseEntity<?> listarAgencias(Pageable pageable){
    	Page<Agencia> agencias = service.listar(pageable);
    	ListaPaginada<AgenciaRepresentationV1> model = AgenciaRepresentationV1.from(agencias);
        return ok(model); 
    }
    
    @ApiOperation(
    		value = "Consultar uma única agência por código.",
    		response = AgenciaRepresentationV1.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agência retornada com sucesso."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso"),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso"),
            @ApiResponse(code = 404, message = "Nenhuma agência encontrada")
    })
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
        
    @ApiOperation(
    		value = "Incluir uma nova agência.",
    		response = AgenciaInclusaoCommand.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Agência criada com sucesso."),
            @ApiResponse(code = 400, message = "Inclusão não permitida por validações.")
    })
    @PostMapping
    public ResponseEntity<?> incluirAgencia(
    		@RequestBody AgenciaInclusaoCommand comando){
    	
        Agencia agenciaIncluida = service.salvar(comando);
        AgenciaRepresentationV1 model = AgenciaRepresentationV1.from(agenciaIncluida);
        return created(model, agenciaIncluida.getCodigo());
    }
    
    @ApiOperation(
    		value = "Alterar informações de uma única agência.",
    		response = AgenciaEdicaoCommand.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agência alterada com sucesso."),
            @ApiResponse(code = 400, message = "Alteração não permitida por validações."),
            @ApiResponse(code = 404, message = "Agência não encontrada")
    })
    @PutMapping(value = "/{codigo}")
    public ResponseEntity<?> editarAgencia(
    		@PathVariable("codigo") Integer codigo,
            @RequestBody AgenciaEdicaoCommand comando){
        
        Agencia agenciaEditada = service.salvar(codigo, comando);
        AgenciaRepresentationV1 model = AgenciaRepresentationV1.from(agenciaEditada);
        return ok(model);
    }
    
    @ApiOperation(
    		value = "Excluir uma agência por código.",
    		response = AgenciaRepresentationV1.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agência removida com sucesso."),
            @ApiResponse(code = 400, message = "Exclusão não permitida por validações."),
            @ApiResponse(code = 404, message = "Agência não encontrada")
    })
    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<?> excluirAgencia(
    		@PathVariable("codigo") Integer codigo){
    	
    	service.excluir(codigo);
        return ok();
    }

}
