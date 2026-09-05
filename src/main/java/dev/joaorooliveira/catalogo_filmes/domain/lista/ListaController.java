package dev.joaorooliveira.catalogo_filmes.domain.lista;

import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaAtualizarDTO;
import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/listas")
public class ListaController {

    private final ListaService listaService;

    public ListaController(ListaService listaService) {
        this.listaService = listaService;
    }

    @PostMapping
    public ResponseEntity<ListaResponseDTO> salvar(@RequestBody @Valid ListaRequestDTO listaRequestDTO) {
        ListaResponseDTO listaResponseDTO = listaService.salvarLista(listaRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(listaResponseDTO.id())
                .toUri();
        return ResponseEntity.created(location).body(listaResponseDTO);
    }

    @PostMapping("/{id}/filmes")
    public ResponseEntity<ListaResponseDTO> adicionarFilmesNaLista(
            @PathVariable Long id,
            @RequestBody List<Long> filmes) {
        ListaResponseDTO listaResponseDTO = listaService.adicionarFilmesNaLista(id, filmes);
        return ResponseEntity.ok(listaResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ListaResponseDTO>> buscar(@RequestParam(required = false) String titulo, Pageable pageable) {
        Page<ListaResponseDTO> listaResponseDTO = listaService.buscarListas(titulo,pageable);
        return ResponseEntity.ok(listaResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaResponseDTO> buscarPorId(@PathVariable Long id) {
        ListaResponseDTO listaResponseDTO = listaService.buscarListaPorId(id);
        return ResponseEntity.ok(listaResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        listaService.deletarLista(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListaResponseDTO> atualizar(@PathVariable Long id,
                                                      @RequestBody @Valid ListaAtualizarDTO listaAtualizarDTO) {
        ListaResponseDTO listaResponseDTO = listaService.atualizarLista(id, listaAtualizarDTO);
        return ResponseEntity.ok(listaResponseDTO);
    }
}
