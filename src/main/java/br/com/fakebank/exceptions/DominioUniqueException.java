package br.com.fakebank.exceptions;

public class DominioUniqueException extends RuntimeException{

    public DominioUniqueException(){
        super("Dom�nio j� existe");
    }

    public DominioUniqueException(String message){
        super(message);
    }
}
