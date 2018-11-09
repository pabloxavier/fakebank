package br.com.fakebank.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fakebank.domain.Agencia;
import br.com.fakebank.representations.AgenciaRepresentationV2;
import br.com.fakebank.service.AgenciaService;

@RestController
@RequestMapping("v2/agencias")
public class AgenciaEndpointV2 extends FakebankEndpoint{
    
    @Autowired
    private AgenciaService service;

    @GetMapping(value = "/{codigo}")
    public ResponseEntity<?> getAgenciaById(
    		@PathVariable("codigo") final Integer codigo){
    	
        Agencia agencia = service.consultarPorCodigo(codigo);
        AgenciaRepresentationV2 model = AgenciaRepresentationV2.from(agencia);
        return ok(model); 
    }
}
