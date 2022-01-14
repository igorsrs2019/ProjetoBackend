package br.com.uolhost.cadastrodejogadores.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.uolhost.cadastrodejogadores.controller.form.AtualizacaoJogadorForm;
import br.com.uolhost.cadastrodejogadores.modelo.Jogador;
import br.com.uolhost.cadastrodejogadores.repository.CadastroRepository;

@RestController
public class JogadorController {
	
	@Autowired
	private CadastroRepository cadastrorepository;
	
	@PostMapping(value= "/jogador",  produces="application/json", consumes="application/json")
	public ResponseEntity cadastrar (@RequestBody Jogador jogador) {
		cadastrorepository.save(jogador);	
		return ResponseEntity.ok("criado!");
	}
	
	@GetMapping(value= "/jogador", produces="application/json", consumes="application/json")
	public  List<Jogador> lista () {
		final List<Jogador> jogadores = cadastrorepository.findAll();
		return (jogadores);
	}
	
	@PutMapping(value = "jogador/{id}", produces="application/json", consumes="application/json")
	@Transactional
	public  ResponseEntity<Jogador> atualizar (@PathVariable Long id, @RequestBody  AtualizacaoJogadorForm form){
		Jogador jogador = form.atualizar(id, cadastrorepository);
		return ResponseEntity.ok(jogador);
	}
	
	@DeleteMapping(value = "jogador/{id}", produces="application/json", consumes="application/json")
	public ResponseEntity<?> excluir (@PathVariable Long id ) {
		cadastrorepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
