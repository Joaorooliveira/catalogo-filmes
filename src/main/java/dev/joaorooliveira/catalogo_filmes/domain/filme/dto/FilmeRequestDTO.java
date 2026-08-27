package dev.joaorooliveira.catalogo_filmes.domain.filme.dto;

import dev.joaorooliveira.catalogo_filmes.domain.diretor.Diretor;
import dev.joaorooliveira.catalogo_filmes.domain.filme.Filme;
import dev.joaorooliveira.catalogo_filmes.domain.filme.enums.GeneroTipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FilmeRequestDTO(
        @NotBlank(message = "Nome nao pode ser nulo ou vazio")
        String nome,

        String descricao,

        @NotNull(message = "Genero nao pode ser nulo")
        GeneroTipo genero,

        LocalDate anoLancamento,

        Integer avaliacao,

        @NotNull(message = "Nao pode ser nulo")
        Boolean assistido,

        @NotNull(message = "Nao pode ser nulo")
        Boolean favorito,

        Long diretorId
) {

    public Filme toEntity(Diretor diretor) {
        Filme filme = new Filme();
        preencher(filme,diretor);
        return filme;
    }

    public void preencher(Filme filme,Diretor diretor) {
        filme.setNome(this.nome);
        filme.setDescricao(this.descricao);
        filme.setGenero(this.genero);
        filme.setAvaliacao(this.avaliacao);
        filme.setAnoLancamento(this.anoLancamento);
        filme.setAssistido(this.assistido);
        filme.setFavorito(this.favorito);
        filme.setDiretor(diretor);
    }
}
