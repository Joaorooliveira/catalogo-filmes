package dev.joaorooliveira.catalogo_filmes.domain.diretor;

import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorFiltroRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorResponseDTO;
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

    public Page<DiretorResponseDTO> listarDiretores(Pageable pageable, DiretorFiltroRequestDTO filtro) {}
}
