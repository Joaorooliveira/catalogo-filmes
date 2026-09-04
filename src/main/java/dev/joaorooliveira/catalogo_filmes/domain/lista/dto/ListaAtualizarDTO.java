package dev.joaorooliveira.catalogo_filmes.domain.lista.dto;

import dev.joaorooliveira.catalogo_filmes.domain.lista.Lista;

public record ListaAtualizarDTO(
        String titulo
) {
    public void preencher(Lista lista) {
        if (this.titulo != null) {
            lista.setTitulo(this.titulo);
        }
    }
}
