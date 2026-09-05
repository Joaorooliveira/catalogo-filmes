package dev.joaorooliveira.catalogo_filmes.domain.lista.dto;

import dev.joaorooliveira.catalogo_filmes.domain.lista.Lista;
import jakarta.validation.constraints.NotBlank;

public record ListaRequestDTO(
        @NotBlank(message = "O título da lista não pode ser nulo ou vazio")
        String titulo
) {
    public Lista toEntity(){
        Lista lista = new Lista();
        lista.setTitulo(this.titulo);
        return lista;
    }

}
