package dev.joaorooliveira.catalogo_filmes.domain.filme;

import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping
    public ResponseEntity<FilmeResponseDTO> salvar(@RequestBody @Valid FilmeRequestDTO filmeRequestDTO) {
        FilmeResponseDTO filmeResponseDTO = filmeService.salvarFilme(filmeRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(filmeResponseDTO.id())
                .toUri();
        return ResponseEntity.created(location).body(filmeResponseDTO);
    }
}
