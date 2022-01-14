package br.com.uolhost.cadastrodejogadores;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import br.com.uolhost.cadastrodejogadores.dto.SuperHeroiJson;

@SpringBootApplication
public class CadastrodejogadoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastrodejogadoresApplication.class, args);
	}

	  @Bean
	    public RestTemplate restTemplate(RestTemplateBuilder builder) {
	        return builder.build();
	    }

	    @Bean
	    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
	        return args -> {
	            final SuperHeroiJson superHeroJson = restTemplate.getForObject(
	                    "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json", 
	                    SuperHeroiJson.class);
	        };
	    }
}
