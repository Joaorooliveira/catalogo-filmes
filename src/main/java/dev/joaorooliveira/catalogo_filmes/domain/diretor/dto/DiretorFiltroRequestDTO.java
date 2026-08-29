package dev.joaorooliveira.catalogo_filmes.domain.diretor.dto;

import java.time.LocalDate;

public record DiretorFiltroRequestDTO(
        String nome,
        LocalDate dataNascimento
) {
}
