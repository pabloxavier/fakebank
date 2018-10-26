package br.com.fakebank.endpoint;

import br.com.fakebank.domain.Conta;
import br.com.fakebank.domain.commands.ContaCorrenteInclusaoCommand;
import br.com.fakebank.domain.commands.ContaPoupancaInclusaoCommand;
import br.com.fakebank.domain.commands.ContaSalarioInclusaoCommand;
import br.com.fakebank.representations.ContaRepresentation;
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

    @PostMapping
    public ResponseEntity<?> incluirContaCorrente(ContaCorrenteInclusaoCommand comando) {
        contaService.salvarContaCorrente(comando);
        return created("Conta Corrente incluida com sucesso");
    }
    @PostMapping
    public ResponseEntity<?> incluirContaSalario(ContaSalarioInclusaoCommand comando) {
        contaService.salvarContaSalario(comando);
        return created("Conta Salario incluida com sucesso");
    }

    @PostMapping
    public ResponseEntity<?> incluirContaPoupanca(ContaPoupancaInclusaoCommand comando) {
        contaService.salvarContaPoupanca(comando);
        return created("Conta Poupanca incluida com sucesso");
    }

}
