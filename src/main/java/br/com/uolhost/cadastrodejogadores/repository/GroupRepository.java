package br.com.uolhost.cadastrodejogadores.repository;

import br.com.uolhost.cadastrodejogadores.modelo.Grupo;
import br.com.uolhost.cadastrodejogadores.modelo.GruposEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Grupo, Long> {
    List<Grupo> findByTime(GruposEnum time);
    boolean existsByCodiNome(String codiNome);
}
