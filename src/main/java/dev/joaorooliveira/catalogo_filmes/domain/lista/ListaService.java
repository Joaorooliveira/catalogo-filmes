package dev.joaorooliveira.catalogo_filmes.domain.lista;

import dev.joaorooliveira.catalogo_filmes.domain.filme.Filme;
import dev.joaorooliveira.catalogo_filmes.domain.filme.FilmeRepository;
import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaAtualizarDTO;
import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaResponseDTO;
import dev.joaorooliveira.catalogo_filmes.infra.exception.EntidadeNaoEncontradaException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListaService {
    private final ListaRepository listaRepository;
    private final FilmeRepository filmeRepository;

    public ListaService(ListaRepository listaRepository, FilmeRepository filmeRepository) {
        this.listaRepository = listaRepository;
        this.filmeRepository = filmeRepository;
    }

    @Transactional
    public ListaResponseDTO salvarLista(ListaRequestDTO listaRequestDTO) {
        Lista lista = listaRepository.save(listaRequestDTO.toEntity());
        return ListaResponseDTO.fromEntity(lista);
    }

    @Transactional
    public ListaResponseDTO adicionarFilmesNaLista(Long id,List<Long> filmes){
        Lista lista = listaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Lista não encontrada com o ID: " + id));

        lista.getFilmes().addAll(filmes.stream()
                .map(filmeId -> filmeRepository.findById(filmeId)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme não encontrado com o ID: " + filmeId)))
                .toList());
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

    @Transactional
    public ListaResponseDTO deletarFilmesDaLista(Long id,List<Long> filmesId){
        Lista lista = listaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Lista não encontrada com o ID: " + id));

        List<Filme> filmesParaRemover = filmesId.stream()
                .map(filmeId -> filmeRepository.findById(filmeId)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme não encontrado com o ID: " + filmeId)))
                .toList();

        lista.getFilmes().removeAll(filmesParaRemover);
        return ListaResponseDTO.fromEntity(lista);
    }

    @Transactional
    public ListaResponseDTO atualizarLista(Long id, ListaAtualizarDTO listaAtualizarDTO) {
        Lista lista = listaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Lista não encontrada com o ID: " + id));
        listaAtualizarDTO.preencher(lista);
        return ListaResponseDTO.fromEntity(lista);
    }




}
