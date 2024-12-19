package br.com.api.contatos.error.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String mensagem) {
        super(mensagem);
    }

}