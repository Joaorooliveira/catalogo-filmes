package dev.joaorooliveira.catalogo_filmes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CatalogoFilmesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoFilmesApplication.class, args);
	}

}
