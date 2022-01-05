package br.com.uolhost.cadastrodejogadores.modelo;



import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Jogador {
	
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		
		private Long id;
		 @NotNull @NotEmpty @Length (min = 5)
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

