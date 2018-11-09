package br.com.fakebank.endpoint;

import br.com.fakebank.domain.Cliente;
import br.com.fakebank.domain.ClienteTelefone;
import br.com.fakebank.domain.commands.AgenciaEdicaoCommand;
import br.com.fakebank.domain.commands.ClienteTelefoneEdicaoCommand;
import br.com.fakebank.domain.commands.ClienteTelefoneInclusaoCommand;
import br.com.fakebank.representations.ClienteTelefoneRepresentation;
import br.com.fakebank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteTelefoneEndpoint extends FakebankEndpoint {

    @Autowired
    ClienteService service;

    @GetMapping(value = "/{codigoCliente}/telefones")
    public ResponseEntity<?> getTelefonesByClienteId(@PathVariable("codigoCliente") Integer codigoCliente) {

        List<ClienteTelefone> clienteTelefones = service.listarTelefonesFromCliente(codigoCliente);
        return ok(ClienteTelefoneRepresentation.from(clienteTelefones));
    }

    @PostMapping(value = "/{codigoCliente}/telefones")
    public ResponseEntity<?> incluirTelefone(
            @PathVariable("codigoCliente") Integer codigoCliente,
            @RequestBody ClienteTelefoneInclusaoCommand comando) {

        Cliente cliente = service.getClienteById(codigoCliente);

        if (cliente == null)
            return notFound("cliente não encontrado");

        ClienteTelefone clienteTelefone = service.salvarTelefone(cliente, comando);
        return ok(ClienteTelefoneRepresentation.from(clienteTelefone));
    }

    @DeleteMapping(value = "/{codigoCliente}/telefones/{codigoTelefone}")
    public ResponseEntity<?> excluirTelefone(
            @PathVariable("codigoCliente") Integer codigoCliente,
            @PathVariable("codigoTelefone") Short codigoTelefone) {

        return service.excluirTelefone(codigoCliente, codigoTelefone) ? ok("excluido com sucesso") : notFound("telefone n�o encontrado");
        }

    
    @PutMapping(value = "/{codigoCliente}/telefones/{codigoTelefone}")
    public ResponseEntity<?> editarTelefone(
    		@PathVariable("codigoCliente") Integer codigoCliente, 
    		@PathVariable("codigoTelefone") Short codigoTelefone, 
    		@RequestBody ClienteTelefoneEdicaoCommand comando){
    	
    	ClienteTelefone telefone = service.salvarTelefone(codigoCliente, codigoTelefone, comando);
    	
    	return created ("editado com sucesso");
    	
    }
    		
    

    
}
