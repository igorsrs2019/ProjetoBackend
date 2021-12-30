package br.com.uolhost.cadastrodejogadores.controller.form;

import br.com.uolhost.cadastrodejogadores.modelo.Jogador;
import br.com.uolhost.cadastrodejogadores.repository.CadastroRepository;

public class AtualizacaoJogadorForm {

	
	private String nome;
	private String email;
	private String telefone;
	private String codinome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCodinome() {
		return codinome;
	}
	public void setCodinome(String codinome) {
		this.codinome = codinome;
	}
	
	
	public Jogador atualizar(Long id, CadastroRepository cadastrorepository) {
		Jogador jogador = cadastrorepository.getById(id);
		
		jogador.setNome(this.nome);
		jogador.setEmail(this.email);
		jogador.setTelefone(this.telefone);
		jogador.setCodinome(this.codinome);
		
		return jogador;
	}
	
}
