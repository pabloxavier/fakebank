package br.com.fakebank.endpoint;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fakebank.common.util.ListaPaginada;
import br.com.fakebank.domain.Movimentacao;
import br.com.fakebank.domain.commands.MovimentacaoDepositoCommand;
import br.com.fakebank.domain.commands.MovimentacaoSaqueCommand;
import br.com.fakebank.domain.commands.MovimentacaoTransferenciaCommand;
import br.com.fakebank.representations.AgenciaRepresentationV1;
import br.com.fakebank.representations.MovimentacaoRepresentation;
import br.com.fakebank.service.MovimentacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("movimentacoes")
public class MovimentacaoEndpoint extends FakebankEndpoint {

    @Autowired
    private MovimentacaoService service;

    @ApiOperation(
            value = "Listar todas as movimentacoes cadastradas.",
            response = MovimentacaoRepresentation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Movimentacao retornadas com sucesso."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso"),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso"),
            @ApiResponse(code = 404, message = "Nenhuma movimentacao encontrada")
    })
    @GetMapping
    public ResponseEntity<?> listar(Pageable pageable){
        Page<Movimentacao> movimentacoes = service.listar(pageable);
        ListaPaginada<MovimentacaoRepresentation> model = MovimentacaoRepresentation.from(movimentacoes);
        return ok(model);
    }

    @ApiOperation(
            value = "Listar a movimentacao pelo codigo.",
            response = MovimentacaoRepresentation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Movimentacao retornadas com sucesso."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso"),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso"),
            @ApiResponse(code = 404, message = "Nenhuma movimentacao encontrada")
    })
    @GetMapping(value = "/{codigo}")
    public ResponseEntity<?> getMovimentacaoById(@PathVariable("codigo") final Integer codigo) {
        Movimentacao movimentacao = service.consultarPorCodigo(codigo);
        return ok(MovimentacaoRepresentation.from(movimentacao));
    }

    @ApiOperation(
            value = "Pesquisa a movimentacao por pelo menos um dos campos: conta, valor da movimentacao, tipo de movimentacao, ou filtro de data.",
            response = MovimentacaoRepresentation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Movimentacao retornada com sucesso."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso"),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso"),
            @ApiResponse(code = 404, message = "Nenhuma movimentacao encontrada")
    })
    @GetMapping(path = "/pesquisa")
    public ResponseEntity<?> pesquisarMovimentacao(
            Pageable pageable,
            @RequestParam(value = "conta", required = true) String conta,
            @RequestParam(value = "valorMovimentacao", required = false) double valorMovimentacao,
            @RequestParam(value = "tipoMovimentacao", required = false) Integer tipoMovimentacao,
            @RequestParam(value = "dataInicio", required = false) LocalDate dataInicio,
            @RequestParam(value = "dataFinal", required = false) LocalDate dataFinal) {

        Page<Movimentacao> movimentacao = service.filtrar(pageable, conta, valorMovimentacao, tipoMovimentacao, dataInicio, dataFinal);
        ListaPaginada<MovimentacaoRepresentation> model = MovimentacaoRepresentation.from(movimentacao);
        return ok(model);
    }

    @ApiOperation(
            value = "Realiza uma movimentacao de transferencia.",
            response = MovimentacaoRepresentation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transferencia realizada com sucesso."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso"),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso"),
            @ApiResponse(code = 404, message = "Nao foi possivel realizar a transferencia.")
    })
    @PostMapping(value = "/transferencia")
    public ResponseEntity<?> transferir(@RequestBody MovimentacaoTransferenciaCommand comando) {
        List<Movimentacao> movimentacao = service.transferir(comando);
        if(movimentacao == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ok(MovimentacaoRepresentation.from(movimentacao));
    }

    @ApiOperation(
            value = "Realiza uma movimentacao de saque.",
            response = MovimentacaoRepresentation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Saque realizado com sucesso."),
            @ApiResponse(code = 400, message = "Saldo insuficiente."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso"),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso"),
            @ApiResponse(code = 404, message = "Conta nao encontrada.")
    })
    @PostMapping(value = "/saque")
    public ResponseEntity<?> sacar(@RequestBody MovimentacaoSaqueCommand comando) {
        Movimentacao movimentacao = service.sacar(comando);
        if(movimentacao == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ok(MovimentacaoRepresentation.from(movimentacao));
    }

    @ApiOperation(
            value = "Realiza uma movimentacao de deposito.",
            response = MovimentacaoRepresentation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deposito realizado com sucesso."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso"),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso"),
            @ApiResponse(code = 404, message = "Conta nao encontrada.")
    })
    @PostMapping(value = "/deposito")
    public ResponseEntity<?> depositar(@RequestBody MovimentacaoDepositoCommand comando) {
        Movimentacao movimentacao = service.depositar(comando);
        if(movimentacao == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ok(MovimentacaoRepresentation.from(movimentacao));
    }

    @ApiOperation(
            value = "Lista as movimentacoes pelo numero da conta.",
            response = MovimentacaoRepresentation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Movimentacao retornada com sucesso."),
            @ApiResponse(code = 401, message = "Recurso sem autorização de acesso"),
            @ApiResponse(code = 403, message = "Acesso negado ao recurso"),
            @ApiResponse(code = 404, message = "Conta nao encontrada.")
    })
    @GetMapping(path = "/conta/{codigoConta}")
    public ResponseEntity<?> listarMovimentacoes(@PathVariable("codigoConta") final String codigoConta, Pageable pageable ){
        Page<Movimentacao> movimentacoes = service.listarMovimentacoesPorConta(codigoConta, pageable);
        ListaPaginada<MovimentacaoRepresentation> model = MovimentacaoRepresentation.from(movimentacoes);
        return ok(model);
    }

}
