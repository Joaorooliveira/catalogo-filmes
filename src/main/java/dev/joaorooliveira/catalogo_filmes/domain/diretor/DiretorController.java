package dev.joaorooliveira.catalogo_filmes.domain.diretor;

import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorFiltroRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/diretores")
public class DiretorController {
    private final DiretorService diretorService;

    public DiretorController(DiretorService diretorService) {
        this.diretorService = diretorService;
    }

    @PostMapping
    public ResponseEntity<DiretorResponseDTO> salvar(@RequestBody @Valid DiretorRequestDTO diretorRequestDTO){
        DiretorResponseDTO diretorResponseDTO = diretorService.salvarDiretor(diretorRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(diretorResponseDTO.id())
                .toUri();
        return ResponseEntity.created(location).body(diretorResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DiretorResponseDTO>> buscar(@PageableDefault(size = 10) Pageable pageable, DiretorFiltroRequestDTO filtro){
        return ResponseEntity.ok(diretorService.buscarDiretores(pageable,filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiretorResponseDTO> buscar(@PathVariable Long id){
        return ResponseEntity.ok(diretorService.buscarDiretorPorId(id));
    }
}
