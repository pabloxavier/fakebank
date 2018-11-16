package br.com.fakebank.exceptions;

import java.util.List;

public class BadRequestException extends RuntimeException {
    
    /**
     * Identificador de serialização da versão da classe
     */
    private static final long serialVersionUID = 1L;
    
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

