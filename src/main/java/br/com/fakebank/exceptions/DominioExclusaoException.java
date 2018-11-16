package br.com.fakebank.exceptions;

public class DominioExclusaoException extends RuntimeException{

	private static final long serialVersionUID = 6471427447783961094L;

	public DominioExclusaoException(){
        super("Domínio está sendo usado");
    }

    public DominioExclusaoException(String message){
        super(message);
    }
}
