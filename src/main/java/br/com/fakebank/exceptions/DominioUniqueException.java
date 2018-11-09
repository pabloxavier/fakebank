package br.com.fakebank.exceptions;

public class DominioUniqueException extends RuntimeException{

    public DominioUniqueException(){
        super("Domínio já existe");
    }

    public DominioUniqueException(String message){
        super(message);
    }
}
