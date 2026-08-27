package dev.joaorooliveira.catalogo_filmes.infra;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}