package dev.joaorooliveira.catalogo_filmes.infra;

public class RegraNegocioException extends RuntimeException{

    public RegraNegocioException(String message) {
        super(message);
    }
}
