package br.com.fakebank.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class FakebankEndpoint {
	
	public ResponseEntity<?> ok(){
		return ResponseEntity.ok()
				             .build();
	}
	
	public <T> ResponseEntity<T> ok(T body){
		return  ResponseEntity.ok(body);
	}
	
	public <T> ResponseEntity<T> created(T body){ 
		return new ResponseEntity<>(body, HttpStatus.CREATED);
	}	
	
	public <T> ResponseEntity<T> notFound(T body){ 
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);	
	}
}
