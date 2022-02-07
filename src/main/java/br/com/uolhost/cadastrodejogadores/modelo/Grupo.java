package br.com.uolhost.cadastrodejogadores.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Grupo {
    private GruposEnum time;
    @Id
    private String codiNome;
}
