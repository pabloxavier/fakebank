package br.com.fakebank.exceptions;

import java.util.List;

public class BadRequestException extends RuntimeException {
    
    private List<MessageErrorDetail> errors;
    
    public BadRequestException(List<MessageErrorDetail> errors) {
        super("Erros de validação.");
        this.errors = errors;
    }

    public BadRequestException(String message, List<MessageErrorDetail> errors) {
        super(message);
        this.errors = errors;
    }
    
    public List<MessageErrorDetail> getErrors() {
        return this.errors;
    }
}

