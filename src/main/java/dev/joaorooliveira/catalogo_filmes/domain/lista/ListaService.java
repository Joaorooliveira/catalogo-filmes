package dev.joaorooliveira.catalogo_filmes.domain.lista;

import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ListaService {
    private final ListaRepository listaRepository;

    public ListaService(ListaRepository listaRepository) {
        this.listaRepository = listaRepository;
    }

    @Transactional
    public ListaResponseDTO salvarLista(ListaRequestDTO listaRequestDTO) {
        Lista lista = listaRepository.save(listaRequestDTO.toEntity());
        return ListaResponseDTO.fromEntity(lista);
    }


}
