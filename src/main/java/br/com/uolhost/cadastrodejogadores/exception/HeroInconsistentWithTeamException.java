package br.com.uolhost.cadastrodejogadores.exception;

public class HeroInconsistentWithTeamException extends Exception {
    public HeroInconsistentWithTeamException(){
        super("Hero inconsistent with the team!");
    }

    public HeroInconsistentWithTeamException(String message){
        super(message);
    }

    public HeroInconsistentWithTeamException(String message, Throwable causeOfError){
        super(message, causeOfError);
    }
}
