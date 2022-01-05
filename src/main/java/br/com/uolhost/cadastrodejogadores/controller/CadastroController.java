package br.com.uolhost.cadastrodejogadores.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.uolhost.cadastrodejogadores.modelo.Jogador;
import br.com.uolhost.cadastrodejogadores.repository.CadastroRepository;



@RestController
public class CadastroController {

	@Autowired
	private CadastroRepository cadastrorepository;
	
	
	@PostMapping("/cadastrar")
	public void cadastrar (@RequestBody @Valid Jogador jogador) {
		cadastrorepository.save(jogador);
		
		
		
	}
	
	
	
	
	
	
}
