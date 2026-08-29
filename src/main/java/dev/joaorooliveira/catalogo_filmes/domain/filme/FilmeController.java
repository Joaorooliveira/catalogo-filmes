package dev.joaorooliveira.catalogo_filmes.domain.filme;

import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeAtualizarDTO;
import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeFiltroRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<FilmeResponseDTO>> buscar(@PageableDefault(size = 10) Pageable pageable,
                                                         FilmeFiltroRequestDTO filtro) {
        return ResponseEntity.ok(filmeService.buscarFilmes(pageable, filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> buscarFilmePorId(@PathVariable Long id) {
        return ResponseEntity.ok(filmeService.buscarFilmePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> atualizar(@PathVariable Long id, @RequestBody FilmeAtualizarDTO filmeAtualizarDTO){
        return ResponseEntity.ok(filmeService.atualizarFilme(id,filmeAtualizarDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        filmeService.deletarFilme(id);
        return ResponseEntity.ok().build();
    }
}
