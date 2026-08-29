package dev.joaorooliveira.catalogo_filmes.domain.diretor;

import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorAtualizarDTO;
import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorFiltroRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorResponseDTO;
import dev.joaorooliveira.catalogo_filmes.infra.exception.EntidadeNaoEncontradaException;
import dev.joaorooliveira.catalogo_filmes.infra.specification.DiretorSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiretorService {

    private final DiretorRepository diretorRepository;

    public DiretorService(DiretorRepository diretorRepository) {
        this.diretorRepository = diretorRepository;
    }

    @Transactional
    public DiretorResponseDTO salvarDiretor(DiretorRequestDTO diretorRequestDTO) {
        var diretor = diretorRepository.save(diretorRequestDTO.toEntity());
        return DiretorResponseDTO.fromDiretor(diretor);
    }

    public Page<DiretorResponseDTO> buscarDiretores(Pageable pageable, DiretorFiltroRequestDTO filtro) {
        return diretorRepository.findAll(DiretorSpecification.comFiltros(filtro),pageable)
                .map(DiretorResponseDTO::fromDiretor);
    }

    public DiretorResponseDTO buscarDiretorPorId(Long id){
        return DiretorResponseDTO.fromDiretor(diretorRepository.findById(id).orElseThrow(
                ()-> new EntidadeNaoEncontradaException("Diretor nao encontrado")));
    }

    @Transactional
    public DiretorResponseDTO atualizarDiretor(Long id, DiretorAtualizarDTO diretorAtualizarDTO){
        var diretor = diretorRepository.findById(id).orElseThrow(
                ()-> new EntidadeNaoEncontradaException("Diretor nao encontrado"));
        diretorAtualizarDTO.preencher(diretor);
        return DiretorResponseDTO.fromDiretor(diretor);
    }

    @Transactional
    public void deletarDiretor(Long id){
        var diretor = diretorRepository.findById(id).orElseThrow(
                ()-> new EntidadeNaoEncontradaException("Diretor nao encontrado"));
        diretorRepository.delete(diretor);
    }

}
