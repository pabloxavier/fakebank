package br.com.fakebank.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fakebank.domain.Conta;
import br.com.fakebank.domain.commands.ContaCorrenteEdicaoCommand;
import br.com.fakebank.domain.commands.ContaCorrenteInclusaoCommand;
import br.com.fakebank.domain.commands.ContaPoupancaEdicaoCommand;
import br.com.fakebank.domain.commands.ContaPoupancaInclusaoCommand;
import br.com.fakebank.domain.commands.ContaSalarioEdicaoCommand;
import br.com.fakebank.domain.commands.ContaSalarioInclusaoCommand;
import br.com.fakebank.representations.ContaCorrenteRepresentation;
import br.com.fakebank.representations.ContaPoupancaRepresentation;
import br.com.fakebank.representations.ContaRepresentation;
import br.com.fakebank.representations.ContaRepresentationClientePF;
import br.com.fakebank.representations.ContaSalarioRepresentation;
import br.com.fakebank.service.ContaService;
import br.com.fakebank.util.ListaPaginada;



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

    @GetMapping(value = "/clientes-pessoa-fisica/{codigoCliente}/contas")
    public ResponseEntity<?>  getContasByIdClientePessoaFisica(@PathVariable("codigoCliente") final Integer codigo, Pageable pageable){
        Page<Conta> contas = contaService.consultarContasPorCodigoClientePessoaFisica(codigo, pageable);
        ListaPaginada<ContaRepresentationClientePF> model = ContaRepresentationClientePF.from(contas);
        return ok(model);
    }
    
    @RequestMapping(value = {"/clientes-pessoa-fisica/{codigoCliente}/contas-correntes",
    "/clientes-pessoa-juridica/{codigoCliente}/contas-correntes"}, method = RequestMethod.POST)
    public ResponseEntity<?> incluirContaCorrente(
            @PathVariable(value="codigoCliente", required=true) Integer cdCliente,
            @RequestBody ContaCorrenteInclusaoCommand comando) {
        Conta conta = contaService.salvarContaCorrente(cdCliente, comando);
        ContaCorrenteRepresentation model = ContaCorrenteRepresentation.from(conta);
        return created(model, conta.getCodigoConta());
    }
    
    @PostMapping(path = "/incluirContaSalario/{codigoCliente}")
    public ResponseEntity<?> incluirContaSalario(
            @PathVariable(value="codigoCliente", required=true) Integer cdCliente,
            @RequestBody ContaSalarioInclusaoCommand comando) {
        Conta conta = contaService.salvarContaSalario(cdCliente, comando);
        ContaSalarioRepresentation model = ContaSalarioRepresentation.from(conta);
        return created(model, conta.getCodigoConta());
    }

    @PostMapping(path = "/incluirContaPoupanca/{codigoCliente}")
    public ResponseEntity<?> incluirContaPoupanca(
            @PathVariable(value="codigoCliente", required=true) Integer cdCliente,
            @RequestBody ContaPoupancaInclusaoCommand comando) {
        Conta conta = contaService.salvarContaPoupanca(cdCliente, comando);
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
