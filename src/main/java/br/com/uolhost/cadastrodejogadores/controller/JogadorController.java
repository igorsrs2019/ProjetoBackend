package br.com.uolhost.cadastrodejogadores.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.uolhost.cadastrodejogadores.dto.JogadorDTO;
import lombok.AllArgsConstructor;
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
import br.com.uolhost.cadastrodejogadores.repository.JogadorRepository;

@RestController
@AllArgsConstructor
public class JogadorController {

	private final JogadorRepository repository;

	@PostMapping(value= "/jogador",  produces="application/json", consumes="application/json")
	public ResponseEntity saveJogador(@RequestBody Jogador jogador){
		repository.save(jogador);
		return ResponseEntity.ok("criado!");
	}
	
	@GetMapping(value= "/jogador", produces="application/json", consumes="application/json")
	public  List<Jogador> listAll(){
		final List<Jogador> jogadores = repository.findAll();
		return (jogadores);
	}

	@GetMapping(value= "/jogador/{id}", produces="application/json", consumes="application/json")
	public  Jogador listById(@PathVariable Long id) {
		final Optional<Jogador> jogadores = repository.findById(id);
		return (jogadores.get());
	}
	
	@PutMapping(value = "jogador/{id}", produces="application/json", consumes="application/json")
	@Transactional
	public  ResponseEntity<JogadorDTO> alterJogador(@PathVariable Long id, @RequestBody JogadorDTO jogador){
		if(jogador != null || id != null){
			repository.getById(id);
			return ResponseEntity.ok(jogador);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(value = "jogador/{id}", produces="application/json", consumes="application/json")
	public ResponseEntity<?> excluir (@PathVariable Long id ) {
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
