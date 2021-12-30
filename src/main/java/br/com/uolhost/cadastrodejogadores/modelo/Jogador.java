package br.com.uolhost.cadastrodejogadores.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Jogador {

		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String nome;
		private String email;
		private String telefone;
		private String codinome;
		@Enumerated(EnumType.STRING)
		private TipoDeGrupo grupo;
		
		
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
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
		public TipoDeGrupo getGrupo() {
			return grupo;
		}
		public void setGrupo(TipoDeGrupo grupo) {
			this.grupo = grupo;
		}

		
		
	}

