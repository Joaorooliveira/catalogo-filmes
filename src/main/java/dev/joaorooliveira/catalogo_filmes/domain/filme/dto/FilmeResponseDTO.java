package dev.joaorooliveira.catalogo_filmes.domain.filme.dto;

import dev.joaorooliveira.catalogo_filmes.domain.filme.Filme;
import dev.joaorooliveira.catalogo_filmes.domain.filme.enums.GeneroTipo;

import java.time.LocalDate;

public record FilmeResponseDTO(
    String nome,
    Long id,
    String descricao,
    GeneroTipo genero,
    LocalDate anoLancamento,
    Integer avaliacao,
    Boolean assistido,
    Boolean favorito,
    String diretorNome
) {

    public static FilmeResponseDTO fromEntity(Filme filme) {
        return new FilmeResponseDTO(
                filme.getNome(),
                filme.getId(),
                filme.getDescricao(),
                filme.getGenero(),
                filme.getAnoLancamento(),
                filme.getAvaliacao(),
                filme.isAssistido(),
                filme.isFavorito(),
                filme.getDiretor() != null
                        ? filme.getDiretor().getNome()
                        : null
        );
    }
}
