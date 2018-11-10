package br.com.fakebank.exceptions;

public class DominioUniqueException extends RuntimeException{

	private static final long serialVersionUID = 6471427447783961094L;

	public DominioUniqueException(){
        super("Domínio já existe");
    }

    public DominioUniqueException(String message){
        super(message);
    }
}
