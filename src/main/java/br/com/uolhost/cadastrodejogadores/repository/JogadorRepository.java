package br.com.uolhost.cadastrodejogadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uolhost.cadastrodejogadores.modelo.Jogador;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long>{

}
