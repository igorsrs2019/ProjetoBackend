package br.com.uolhost.cadastrodejogadores.controller;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.uolhost.cadastrodejogadores.controller.form.AtualizacaoJogadorForm;
import br.com.uolhost.cadastrodejogadores.modelo.Jogador;
import br.com.uolhost.cadastrodejogadores.repository.CadastroRepository;

@RestController
public class AtualizacaoController {
	
	@Autowired
	private CadastroRepository cadastrorepository;
	
	
	@PutMapping("atualizar/{id}")
	@Transactional
	public  ResponseEntity<Jogador> atualizar (@PathVariable Long id, @RequestBody  AtualizacaoJogadorForm form){
	Jogador jogador = form.atualizar(id, cadastrorepository);
	
	return ResponseEntity.ok(new Jogador(jogador));
	
}
}
