package br.com.uolhost.cadastrodejogadores.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Data
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
	}

