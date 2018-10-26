package br.com.fakebank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<?> handleNaoEncontradoException(NaoEncontradoException exception){
		String message = exception.getMessage();
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	
    @ExceptionHandler(RequisicaoMalFormada.class)
    public ResponseEntity<?> handleResourceBadRequestException(RequisicaoMalFormada exception) {
        
        MessageErrorResponse message = new MessageErrorResponse();
        
        message.setTitulo("Dados incorretos");
        
        message.setMessage(exception.getMessage());
        
        message.setMessages(exception.getErrors());
        
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
