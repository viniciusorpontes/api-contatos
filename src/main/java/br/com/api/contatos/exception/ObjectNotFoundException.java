package br.com.api.contatos.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String mensagem) {
        super(mensagem);
    }

}