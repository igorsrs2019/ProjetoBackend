package br.com.uolhost.cadastrodejogadores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uolhost.cadastrodejogadores.modelo.Jogador;
import br.com.uolhost.cadastrodejogadores.repository.CadastroRepository;


@RestController
public class ListarController {

	
	@Autowired
	private CadastroRepository cadastrorepository;
	
	@GetMapping("/listar")
	public  List<Jogador> lista () {
		
		List<Jogador> jogadores = cadastrorepository.findAll();
			return (jogadores);
	}
}
