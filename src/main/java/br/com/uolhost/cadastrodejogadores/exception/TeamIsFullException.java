package br.com.uolhost.cadastrodejogadores.exception;

public class TeamIsFullException extends Exception {
    public TeamIsFullException(){
        super("The team is full!");
    }

    public TeamIsFullException(String message){
        super(message);
    }

    public TeamIsFullException(String message, Throwable causeOfError){
        super(message, causeOfError);
    }
}
