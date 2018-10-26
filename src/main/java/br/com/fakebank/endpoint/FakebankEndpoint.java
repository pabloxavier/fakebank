package br.com.fakebank.endpoint;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerMapping;

public abstract class FakebankEndpoint {
	
	public ResponseEntity<?> ok(){
		return ResponseEntity.ok()
				             .build();
	}
	
	public <T> ResponseEntity<T> ok(T body){
		return  ResponseEntity.ok(body);
	}

	/***
	 * Montar uma resposta REST HTTP para status 201 - Created
	 * @param body Corpo de resposta para retorno
	 * @param ids Códigos que serão retornados no 'location' do Header
	 * @return
	 */
    public<T> ResponseEntity<T> created(T body, Object... ids) {
        String pathIds = "";
        for (Object id : ids) {
            pathIds += "/" + id.toString();
        }
        
        Object path = RequestContextHolder
            .getRequestAttributes()
            .getAttribute(
                    HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE,
                    RequestAttributes.SCOPE_REQUEST);
        
        URI location = URI.create(path.toString() + pathIds);
        
        return ResponseEntity.created(location).body(body);
    }
	
	public <T> ResponseEntity<T> notFound(T body){ 
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);	
	}
}
