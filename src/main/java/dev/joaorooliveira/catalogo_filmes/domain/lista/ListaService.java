package dev.joaorooliveira.catalogo_filmes.domain.lista;

import org.springframework.stereotype.Service;

@Service
public class ListaService {
    private final ListaRepository listaRepository;

    public ListaService(ListaRepository listaRepository) {
        this.listaRepository = listaRepository;
    }

}
