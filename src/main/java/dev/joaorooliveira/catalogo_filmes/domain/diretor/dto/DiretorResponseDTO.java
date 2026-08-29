package dev.joaorooliveira.catalogo_filmes.domain.diretor.dto;

import dev.joaorooliveira.catalogo_filmes.domain.diretor.Diretor;

import java.time.LocalDate;

public record DiretorResponseDTO(
        Long id,
        String nome,
        LocalDate dataNascimento
) {

    public static DiretorResponseDTO fromDiretor(Diretor diretor) {
        return new DiretorResponseDTO(
                diretor.getId(),
                diretor.getNome(),
                diretor.getDataNascimento()
        );
    }
}
