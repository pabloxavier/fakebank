package br.com.fakebank.endpoint;

import br.com.fakebank.domain.Conta;
import br.com.fakebank.representations.ContaRepresentation;
import br.com.fakebank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
