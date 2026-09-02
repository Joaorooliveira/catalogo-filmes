package dev.joaorooliveira.catalogo_filmes.domain.lista;

import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaResponseDTO;
import dev.joaorooliveira.catalogo_filmes.infra.exception.EntidadeNaoEncontradaException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ListaResponseDTO> buscarListas(
            String titulo,
            Pageable pageable
    ) {
        if (titulo == null || titulo.isBlank()) {
            return listaRepository.findAll(pageable)
                    .map(ListaResponseDTO::fromEntity);
        }
        return listaRepository.findByTituloContainingIgnoreCase(titulo, pageable)
                .map(ListaResponseDTO::fromEntity);
    }

    public ListaResponseDTO buscarListaPorId(Long id) {
        Lista lista = listaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Lista não encontrada com o ID: " + id));
        return ListaResponseDTO.fromEntity(lista);
    }

    @Transactional
    public void deletarLista(Long id){
        Lista lista = listaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Lista não encontrada com o ID: " + id));
        listaRepository.delete(lista);
    }



}
