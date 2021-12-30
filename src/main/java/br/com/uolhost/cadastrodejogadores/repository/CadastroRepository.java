package br.com.uolhost.cadastrodejogadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uolhost.cadastrodejogadores.modelo.Jogador;

public interface CadastroRepository extends JpaRepository<Jogador, Long>{

}
