package br.com.uolhost.cadastrodejogadores.handler;

import br.com.uolhost.cadastrodejogadores.dto.DetailErrorHttp;
import br.com.uolhost.cadastrodejogadores.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(HeroInconsistentWithTeamException.class)
    public ResponseEntity<DetailErrorHttp> HeroInconsistentWithTeamException(HeroInconsistentWithTeamException ex, HttpServletRequest request){
        final DetailErrorHttp error = new DetailErrorHttp();
        error.setError("400");
        error.setErrorTitle("An error has occurred, check the team and hero... please correct it and come back here!");
        error.setErrorMessageOfDeveloper("Please verify the catalog of error, and good lucky my friend!");
        error.setErrorDate(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ResourceHttpIsNotAvailableException.class)
    public ResponseEntity<DetailErrorHttp> ResourceHttpIsNotAvailableException(ResourceHttpIsNotAvailableException ex, HttpServletRequest request){
        final DetailErrorHttp error = new DetailErrorHttp();
        error.setError("408");
        error.setErrorTitle("Sorry! This resource is not available");
        error.setErrorMessageOfDeveloper("Please verify the catalog of error, and good lucky my friend!");
        error.setErrorDate(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(error);
    }

    @ExceptionHandler(TeamIsFullException.class)
    public ResponseEntity<DetailErrorHttp> teamIsFullException(TeamIsFullException ex, HttpServletRequest request){
        final DetailErrorHttp error = new DetailErrorHttp();
        error.setError("400");
        error.setErrorTitle("Sorry! This group is full, please try other group :)");
        error.setErrorMessageOfDeveloper("Please verify the catalog of error, and good lucky my friend!");
        error.setErrorDate(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(PlayerIsNotFoundException.class)
    public ResponseEntity<DetailErrorHttp> handlePlayerIsNotFoundException(PlayerIsNotFoundException ex, HttpServletRequest request){
        final DetailErrorHttp error = new DetailErrorHttp();
        error.setError("404");
        error.setErrorTitle("Sorry! This player is not found, please try save and go back here again :)");
        error.setErrorMessageOfDeveloper("Please verify the catalog of error, and good lucky my friend!");
        error.setErrorDate(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
