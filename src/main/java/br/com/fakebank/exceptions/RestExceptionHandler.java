package br.com.fakebank.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception){
        String message = exception.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException exception) {
        
        MessageErrorResponse message = new MessageErrorResponse();
        
        message.setTitulo("Dados incorretos");
        
        message.setMessage(exception.getMessage());
        
        message.setMessages(exception.getErrors());
        
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(DominioUniqueException.class)
    public ResponseEntity<?> handleResourceBadRequestExceptionDominio(DominioUniqueException exception) {
        
        MessageErrorResponse message = new MessageErrorResponse();
        
        message.setTitulo("Dados incorretos");
        
        message.setMessage(exception.getMessage());
        
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(DominioExclusaoException.class)
    public ResponseEntity<?> handleResourceExclusaoExceptionDominio(DominioExclusaoException exception) {
        
        MessageErrorResponse message = new MessageErrorResponse();
        
        message.setTitulo("Exclusao invalida");
        
        message.setMessage(exception.getMessage());
        
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
