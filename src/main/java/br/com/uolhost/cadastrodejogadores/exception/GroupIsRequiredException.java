package br.com.uolhost.cadastrodejogadores.exception;

public class GroupIsRequiredException extends Exception {
    public GroupIsRequiredException(){
        super("Group is necessary!");
    }

    public GroupIsRequiredException(String message){
        super(message);
    }

    public GroupIsRequiredException(String message, Throwable causeOfError){
        super(message, causeOfError);
    }
}
