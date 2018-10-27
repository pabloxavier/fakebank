package br.com.fakebank.endpoint;

import br.com.fakebank.domain.Conta;
import br.com.fakebank.domain.commands.*;
import br.com.fakebank.representations.ContaCorrenteRepresentation;
import br.com.fakebank.representations.ContaPoupancaRepresentation;
import br.com.fakebank.representations.ContaRepresentation;
import br.com.fakebank.representations.ContaSalarioRepresentation;
import br.com.fakebank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("conta")
public class ContaEndPoint extends FakebankEndpoint {
    @Autowired
    private ContaService contaService;

    @GetMapping(value = "/{codigoConta}")
    public ResponseEntity<?>  getContaById(@PathVariable("codigoConta") final String codigo){
        Conta conta = contaService.consultarPorCodigo(codigo);
        return ok(ContaRepresentation.from(conta));
    }

    @PostMapping(path = "/incluirContaCorrente/{codigoCliente}")
    public ResponseEntity<?> incluirContaCorrente(
            @PathVariable(value="codigoCliente", required=true) Integer cdCliente,
            @RequestBody ContaCorrenteInclusaoCommand comando) {
        Conta conta = contaService.salvarContaCorrente(comando);
        ContaCorrenteRepresentation model = ContaCorrenteRepresentation.from(conta);
        return created(model, conta.getCodigoConta());
    }
    @PostMapping(path = "/incluirContaSalario/{codigoCliente}")
    public ResponseEntity<?> incluirContaSalario(
            @PathVariable(value="codigoCliente", required=true) Integer cdCliente,
            @RequestBody ContaSalarioInclusaoCommand comando) {
        Conta conta = contaService.salvarContaSalario(comando);
        ContaSalarioRepresentation model = ContaSalarioRepresentation.from(conta);
        return created(model, conta.getCodigoConta());
    }

    @PostMapping(path = "/incluirContaPoupanca/{codigoCliente}")
    public ResponseEntity<?> incluirContaPoupanca(
            @PathVariable(value="codigoCliente", required=true) Integer cdCliente,
            @RequestBody ContaPoupancaInclusaoCommand comando) {
        Conta conta = contaService.salvarContaPoupanca(comando);
        ContaPoupancaRepresentation model = ContaPoupancaRepresentation.from(conta);
        return created(model, conta.getCodigoConta());
    }

    @PutMapping(value = "/alterarContaCorrente/{codigoConta}")
    public ResponseEntity<?> alterarContaCorrente(
            @PathVariable(value="codigoConta", required=true) String cdConta,
            @RequestBody ContaCorrenteEdicaoCommand comando){

        return contaService.alterarContaCorrente(cdConta, comando) != null ? ok("editado com sucesso") : notFound("conta nao encontrada");
    }
    @PutMapping(value = "/alterarContaPoupanca/{codigoConta}")
    public ResponseEntity<?> alterarContaPoupanca(
            @PathVariable(value="codigoConta", required=true) String cdConta,
            @RequestBody ContaPoupancaEdicaoCommand comando){

        return contaService.alterarContaPoupanca(cdConta, comando) != null ? ok("editado com sucesso") : notFound("conta nao encontrada");
    }
    @PutMapping(value = "/alterarContaSalario/{codigoConta}")
    public ResponseEntity<?> alterarContaSalario(
            @PathVariable(value="codigoConta", required=true) String cdConta,
            @RequestBody ContaSalarioEdicaoCommand comando){

        return contaService.alterarContaSalario(cdConta, comando) != null ? ok("editado com sucesso") : notFound("conta nao encontrada");
    }
}
