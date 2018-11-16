package br.com.fakebank.endpoint;

import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.ClienteTelefone;
import br.com.fakebank.domain.TipoPessoa;
import br.com.fakebank.domain.commands.ClienteTelefoneEdicaoCommand;
import br.com.fakebank.domain.commands.ClienteTelefoneInclusaoCommand;
import br.com.fakebank.domain.converters.TipoPessoaConverter;
import br.com.fakebank.exceptions.NotFoundException;
import br.com.fakebank.representations.ClienteTelefoneRepresentation;
import br.com.fakebank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping({"clientes-pessoa-fisica", "clientes-pessoa-juridica"})
public class ClienteTelefoneEndpoint extends FakebankEndpoint {

    @Autowired
    ClienteService service;

    @GetMapping(value = "/{codigoCliente}/telefones")
    public ResponseEntity<?> getTelefonesByClienteId(
            @PathVariable("codigoCliente") Integer codigoCliente,
            Pageable pageable,
            HttpServletRequest request) {

        verificaExistenciaClienteByUri(request.getRequestURI(), codigoCliente);

        Page<ClienteTelefone> clienteTelefones = service.listarTelefonesFromCliente(codigoCliente, pageable);
        return ok(ClienteTelefoneRepresentation.from(clienteTelefones));
    }

    @PostMapping(value = "/{codigoCliente}/telefones")
    public ResponseEntity<?> incluirTelefone(
            @PathVariable("codigoCliente") Integer codigoCliente,
            @RequestBody ClienteTelefoneInclusaoCommand comando,
            HttpServletRequest request) {

        Cliente cliente = getClienteByUri(request.getRequestURI(), codigoCliente);

        ClienteTelefone clienteTelefone = service.salvarTelefone(cliente, comando);
        return ok(ClienteTelefoneRepresentation.from(clienteTelefone));
    }

    @DeleteMapping(value = "/{codigoCliente}/telefones/{codigoTelefone}")
    public ResponseEntity<?> excluirTelefone(
            @PathVariable("codigoCliente") Integer codigoCliente,
            @PathVariable("codigoTelefone") Short codigoTelefone,
            HttpServletRequest request) {

        verificaExistenciaClienteByUri(request.getRequestURI(), codigoCliente);

        return service.excluirTelefone(codigoCliente, codigoTelefone) ? ok("excluido com sucesso") : notFound("telefone n�o encontrado");
    }

    @GetMapping(value = "/{codigoCliente}/telefones/{codigoTelefone}")
    public ResponseEntity<?> editarTelefone(
            @PathVariable("codigoCliente") Integer codigoCliente,
            @PathVariable("codigoTelefone") Short codigoTelefone,
            HttpServletRequest request) {

        verificaExistenciaClienteByUri(request.getRequestURI(), codigoCliente);

        ClienteTelefone telefone = service.getTelefoneById(codigoCliente, codigoTelefone);

        if (telefone == null)
            return notFound("Telefone não encontrado");

        return ok(ClienteTelefoneRepresentation.from(telefone));
    }


    @PutMapping(value = "/{codigoCliente}/telefones/{codigoTelefone}")
    public ResponseEntity<?> editarTelefone(
            @PathVariable("codigoCliente") Integer codigoCliente,
            @PathVariable("codigoTelefone") Short codigoTelefone,
            @RequestBody ClienteTelefoneEdicaoCommand comando,
            HttpServletRequest request) {

        verificaExistenciaClienteByUri(request.getRequestURI(), codigoCliente);

        ClienteTelefone telefone = service.salvarTelefone(codigoCliente, codigoTelefone, comando);

        return created("editado com sucesso");
    }

    private boolean verificaExistenciaClienteByUri(String uri, Integer codigoCliente) {
        Cliente cliente = getClienteByUri(uri, codigoCliente);
        return cliente != null;
    }

    private Cliente getClienteByUri(String uri, Integer codigoCliente) {
        String tipoPessoaUri = uri.split("/")[1].replace("clientes-", "");

        TipoPessoaConverter tipoPessoaConverter = new TipoPessoaConverter();
        TipoPessoa tipoPessoa = tipoPessoaConverter.convert(tipoPessoaUri);

        if (tipoPessoa == TipoPessoa.INDEFINIDO) {
            throw new NotFoundException("Tipo de pessoa indefinido");
        }

        Cliente cliente = service.getClienteById(codigoCliente);

        if (cliente == null || (cliente.getPessoa().getTipoPessoa() != tipoPessoa)) {
            throw new NotFoundException("Cliente não encontrado");
        }

        return null;
    }

}
