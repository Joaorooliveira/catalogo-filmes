package dev.joaorooliveira.catalogo_filmes.domain.lista.dto;

import java.util.List;

public record ListaAdicionarFilmesDTO(
        List<Long> filmesIds
) {
}
