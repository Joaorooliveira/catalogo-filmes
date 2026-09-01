package dev.joaorooliveira.catalogo_filmes.domain.lista.dto;

import dev.joaorooliveira.catalogo_filmes.domain.lista.Lista;

public record ListaRequestDTO(
        String titulo
) {
    public Lista toEntity(){
        Lista lista = new Lista();
        lista.setTitulo(this.titulo);
        return lista;
    }

}
