package br.com.uolhost.cadastrodejogadores.exception;

public class NameIsNotAvailableException extends Throwable {
    public NameIsNotAvailableException(){
        super("The name is not available, try another name or team!");
    }

    public NameIsNotAvailableException(String message){
        super(message);
    }

    public NameIsNotAvailableException(String message, Throwable causeOfError){
        super(message, causeOfError);
    }
}
