package dev.joaorooliveira.catalogo_filmes.domain.lista;

import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaResponseDTO;
import jakarta.validation.Valid;
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


}
