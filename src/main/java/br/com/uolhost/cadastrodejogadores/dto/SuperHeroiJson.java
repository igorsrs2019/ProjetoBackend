package br.com.uolhost.cadastrodejogadores.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperHeroiJson {
	private Map<String, String> vingadores;

	public Map<String, String> getVingadores() {
		return vingadores;
	}

	public void setVingadores(Map<String, String> vingadores) {
		this.vingadores = vingadores;
	}
	
	
	
	
}
