package dev.joaorooliveira.catalogo_filmes.domain.filme.dto;

import dev.joaorooliveira.catalogo_filmes.domain.filme.enums.GeneroTipo;

import java.time.LocalDate;

public record FilmeFiltroRequestDTO(
        String nome,
        String descricao,
        GeneroTipo genero,
        LocalDate anoLancamento,
        Integer avaliacao,
        Boolean assistido,
        Boolean favorito,
        Long diretorId
) {
}
