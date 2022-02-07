package br.com.uolhost.cadastrodejogadores.controller;

import javax.transaction.Transactional;

import br.com.uolhost.cadastrodejogadores.dto.JogadorDTO;
import br.com.uolhost.cadastrodejogadores.exception.*;
import br.com.uolhost.cadastrodejogadores.services.JogadorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.uolhost.cadastrodejogadores.modelo.Jogador;

import java.util.Arrays;
import java.util.List;

@RestController
public class JogadorController {

	@Autowired
	private JogadorService service;

	@GetMapping(value = "/codinomes", produces="application/json")
	public ResponseEntity<List<String>> availableNames() throws ResourceHttpIsNotAvailableException, JsonProcessingException {
		return service.getCodiNomesAvailable();
	}

	@GetMapping(value= "/jogadores", produces="application/json")
	public ResponseEntity listAllJogadores(){
		return service.getPlayers();
	}

	@GetMapping(value= "/jogador/{id}", produces="application/json")
	public ResponseEntity listById(@PathVariable Long id) throws PlayerIsNotFoundException {
		return service.getPlayer(id);
	}

	@PostMapping(value= "/jogador",  produces="application/json", consumes="application/json")
	public ResponseEntity saveJogador(@RequestBody JogadorDTO jogador) throws TeamIsFullException, NameIsNotAvailableException, ResourceHttpIsNotAvailableException, JsonProcessingException {
		return service.save(jogador);
	}
	
	@PutMapping(value = "jogador/{id}", produces="application/json", consumes="application/json")
	@Transactional
	public  ResponseEntity alterJogador(@PathVariable Long id, @RequestBody Jogador jogador) throws HeroInconsistentWithTeamException, GroupIsRequiredException, NameIsNotAvailableException, ResourceHttpIsNotAvailableException, JsonProcessingException, PlayerIsNotFoundException {
		if(jogador != null || id != null){
			jogador.setId(id);
			return service.alterPlayer(jogador);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(value = "jogador/{id}", produces="application/json")
	public ResponseEntity excluir (@PathVariable Long id ) throws PlayerIsNotFoundException {
		this.service.deletePlayer(id);
		return ResponseEntity.noContent().build();
	}

}
