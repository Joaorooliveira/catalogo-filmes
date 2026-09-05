package dev.joaorooliveira.catalogo_filmes.domain.lista.dto;

import dev.joaorooliveira.catalogo_filmes.domain.lista.Lista;
import jakarta.validation.constraints.NotBlank;

public record ListaAtualizarDTO(
        @NotBlank(message = "O título da lista não pode ser nulo ou vazio")
        String titulo
) {
    public void preencher(Lista lista) {
        if (this.titulo != null) {
            lista.setTitulo(this.titulo);
        }
    }
}
