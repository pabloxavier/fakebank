package br.com.fakebank.common.exceptions;

public class NotFoundException extends RuntimeException{

    /**
     * Identificador de serialização da versão da classe
     */
    private static final long serialVersionUID = 1L;

    public NotFoundException(){
        super("nenhum recurso encontrado");
    }

    public NotFoundException(String message){
        super(message);
    }
}
