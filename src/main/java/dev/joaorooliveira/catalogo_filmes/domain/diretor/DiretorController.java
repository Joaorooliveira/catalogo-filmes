package dev.joaorooliveira.catalogo_filmes.domain.diretor;

import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
}
