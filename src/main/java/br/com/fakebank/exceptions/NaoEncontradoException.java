package br.com.fakebank.exceptions;

public class NaoEncontradoException extends RuntimeException{

	public NaoEncontradoException(){
		super("nenhum recurso encontrado");
	}

	public NaoEncontradoException(String message){
		super(message);
	}
}
