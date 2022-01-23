package br.com.uolhost.cadastrodejogadores.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class XmlDTO {
    @JsonProperty("codinomes")
    private List<String> codNames;
}
