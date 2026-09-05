package dev.joaorooliveira.catalogo_filmes.infra.springdoc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Catálogo de Filmes API")
                        .description(
                                """
                                API para gerenciamento de um catálogo de filmes, incluindo:
                                
                                - Cadastro e gerenciamento de filmes
                                - Cadastro e gerenciamento de diretores
                                - Criação e gerenciamento de listas de filmes
                                - Associação de filmes às listas
                                - Filtros e consultas paginadas
                                
                                Projeto desenvolvido com Spring Boot para fins educacionais.
                                """
                        )
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("João Oliveira")
                                .email("oliveira.joaov@proton.me"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                )
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor Local")
                ))
                .externalDocs(
                        new ExternalDocumentation()
                                .description("Documentação do Projeto")
                                .url("https://github.com/Joaorooliveira/catalogo-filmes")
                );
    }
}