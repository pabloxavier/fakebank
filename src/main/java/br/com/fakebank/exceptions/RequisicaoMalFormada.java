package br.com.fakebank.exceptions;

import java.util.List;

public class RequisicaoMalFormada extends RuntimeException {
    
    private List<MessageErrorDetail> errors;
    
    public RequisicaoMalFormada(List<MessageErrorDetail> errors) {
        super("Erros de validação.");
        this.errors = errors;
    }

    public RequisicaoMalFormada(String message, List<MessageErrorDetail> errors) {
        super(message);
        this.errors = errors;
    }
    
    public List<MessageErrorDetail> getErrors() {
        return this.errors;
    }
}

