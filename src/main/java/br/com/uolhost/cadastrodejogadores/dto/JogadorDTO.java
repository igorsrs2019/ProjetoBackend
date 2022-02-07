package br.com.uolhost.cadastrodejogadores.dto;

import br.com.uolhost.cadastrodejogadores.modelo.GruposEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JogadorDTO {
    private String nome;
    private String email;
    private String telefone;
    private String codinome;
    private GruposEnum grupo;
}
