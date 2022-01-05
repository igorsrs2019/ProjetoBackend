package br.com.uolhost.cadastrodejogadores.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.uolhost.cadastrodejogadores.repository.CadastroRepository;

@RestController
public class ExclusaoController {

	
	
	@Autowired
	private CadastroRepository cadastrorepository;

	@DeleteMapping("excluir/{id}")
	
	public ResponseEntity<?> excluir (@PathVariable @Valid  Long id ) {
		cadastrorepository.deleteById(id);
		return ResponseEntity.ok().build();

		
	}
}
