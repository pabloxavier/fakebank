package br.com.fakebank.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super("nenhum recurso encontrado");
    }

    public NotFoundException(String message){
        super(message);
    }
}
