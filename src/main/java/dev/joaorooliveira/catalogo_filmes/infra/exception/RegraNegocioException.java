package dev.joaorooliveira.catalogo_filmes.infra.exception;

public class RegraNegocioException extends RuntimeException{

    public RegraNegocioException(String message) {
        super(message);
    }
}
