package dev.joaorooliveira.catalogo_filmes.domain.filme;

import dev.joaorooliveira.catalogo_filmes.domain.diretor.Diretor;
import dev.joaorooliveira.catalogo_filmes.domain.diretor.DiretorRepository;
import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeFiltroRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeResponseDTO;
import dev.joaorooliveira.catalogo_filmes.infra.exception.EntidadeNaoEncontradaException;
import dev.joaorooliveira.catalogo_filmes.infra.specification.FilmeSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final DiretorRepository diretorRepository;

    public FilmeService(FilmeRepository filmeRepository, DiretorRepository diretorRepository) {
        this.filmeRepository = filmeRepository;
        this.diretorRepository = diretorRepository;
    }

    @Transactional
    public FilmeResponseDTO salvarFilme(FilmeRequestDTO filmeRequestDTO) {
        Diretor diretor = diretorRepository.findById(filmeRequestDTO.diretorId()).orElseThrow(
                ()-> new EntidadeNaoEncontradaException("Diretor nao encontrado"));
        Filme filme = filmeRepository.save(filmeRequestDTO.toEntity(diretor));
        return FilmeResponseDTO.fromEntity(filme);
    }

    public Page<FilmeResponseDTO> buscarFilmes(Pageable pageable, FilmeFiltroRequestDTO filtro) {
        return filmeRepository.findAll(FilmeSpecification.comFiltros(filtro),pageable)
                .map(FilmeResponseDTO::fromEntity);
    }

    public FilmeResponseDTO buscarFilmePorId(Long id) {
        Filme filme = filmeRepository.findById(id).orElseThrow(
                ()-> new EntidadeNaoEncontradaException("Filme não encontrado"));
        return FilmeResponseDTO.fromEntity(filme);
    }

    @Transactional
    public void deletarFilme(Long id){
        Filme filme = filmeRepository.findById(id).orElseThrow(
                ()-> new EntidadeNaoEncontradaException("Filme não encontrado"));
        filmeRepository.delete(filme);
    }

}
