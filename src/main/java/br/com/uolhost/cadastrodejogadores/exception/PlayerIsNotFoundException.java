package br.com.uolhost.cadastrodejogadores.exception;

public class PlayerIsNotFoundException extends Throwable {
    public PlayerIsNotFoundException(){
        super("The player is not found, please verify the id and  try again!");
    }

    public PlayerIsNotFoundException(String message){
        super(message);
    }

    public PlayerIsNotFoundException(String message, Throwable causeOfError){
        super(message, causeOfError);
    }
}
