package dev.joaorooliveira.catalogo_filmes.domain.diretor.dto;

import dev.joaorooliveira.catalogo_filmes.domain.diretor.Diretor;
import dev.joaorooliveira.catalogo_filmes.domain.filme.Filme;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record DiretorRequestDTO(
        @NotBlank(message = "Nome nao pode ser nulo ou vazio")
        String nome,

        @Past
        LocalDate dataNascimento
) {

    public Diretor toEntity() {
        Diretor diretor = new Diretor();
        return diretor;
    }

    public void preencher(Diretor diretor) {
        diretor.setNome(this.nome);
        diretor.setDataNascimento(this.dataNascimento);
    }
}
