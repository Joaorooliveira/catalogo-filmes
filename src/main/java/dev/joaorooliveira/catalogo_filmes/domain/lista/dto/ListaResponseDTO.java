package dev.joaorooliveira.catalogo_filmes.domain.lista.dto;

import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeResponseDTO;
import dev.joaorooliveira.catalogo_filmes.domain.lista.Lista;

import java.util.List;

public record ListaResponseDTO(
        Long id,
        String titulo,
        List<FilmeResponseDTO> filmes
) {

    public static ListaResponseDTO fromEntity(Lista lista) {
        return new ListaResponseDTO(
                lista.getId(),
                lista.getTitulo(),
                lista.getFilmes().stream().map(FilmeResponseDTO::fromEntity).toList()
        );
    }


}