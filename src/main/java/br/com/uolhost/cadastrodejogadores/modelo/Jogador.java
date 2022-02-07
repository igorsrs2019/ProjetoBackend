package br.com.uolhost.cadastrodejogadores.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class Jogador {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "grupo_id")
	private Grupo grupo;
}

