package dev.joaorooliveira.catalogo_filmes.domain.lista;

import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaAtualizarDTO;
import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.lista.dto.ListaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Listas",
        description = "Endpoints para gerenciamento de listas de filmes"
)
public class ListaController {

    private final ListaService listaService;

    public ListaController(ListaService listaService) {
        this.listaService = listaService;
    }

    @PostMapping
    @Operation(
            summary = "Cria uma nova lista",
            description = "Cria uma nova lista de filmes no catálogo."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Lista criada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            )
    })
    public ResponseEntity<ListaResponseDTO> salvar(
            @RequestBody @Valid ListaRequestDTO listaRequestDTO
    ) {
        ListaResponseDTO listaResponseDTO =
                listaService.salvarLista(listaRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(listaResponseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(listaResponseDTO);
    }


    @PostMapping("/{id}/filmes")
    @Operation(
            summary = "Adiciona filmes a uma lista",
            description = "Adiciona um ou mais filmes existentes à lista informada."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Filmes adicionados à lista com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Lista ou filme não encontrado"
            )
    })
    public ResponseEntity<ListaResponseDTO> adicionarFilmesNaLista(

            @Parameter(
                    description = "ID da lista que receberá os filmes",
                    example = "1"
            )
            @PathVariable Long id,

            @RequestBody List<Long> filmes
    ) {
        ListaResponseDTO listaResponseDTO =
                listaService.adicionarFilmesNaLista(id, filmes);

        return ResponseEntity.ok(listaResponseDTO);
    }


    @GetMapping
    @Operation(
            summary = "Busca listas",
            description = "Retorna uma lista paginada de listas de filmes. É possível filtrar pelo título."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listas encontradas com sucesso"
            )
    })
    public ResponseEntity<Page<ListaResponseDTO>> buscar(
            @RequestParam(required = false) String titulo,
            Pageable pageable
    ) {
        Page<ListaResponseDTO> listaResponseDTO =
                listaService.buscarListas(titulo, pageable);

        return ResponseEntity.ok(listaResponseDTO);
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Busca uma lista pelo ID",
            description = "Retorna uma lista específica de filmes a partir do seu ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista encontrada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Lista não encontrada"
            )
    })
    public ResponseEntity<ListaResponseDTO> buscarPorId(

            @Parameter(
                    description = "ID da lista",
                    example = "1"
            )
            @PathVariable Long id
    ) {
        ListaResponseDTO listaResponseDTO =
                listaService.buscarListaPorId(id);

        return ResponseEntity.ok(listaResponseDTO);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remove uma lista",
            description = "Remove uma lista de filmes do catálogo."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Lista removida com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Lista não encontrada"
            )
    })
    public ResponseEntity<Void> deletar(

            @Parameter(
                    description = "ID da lista que será removida",
                    example = "1"
            )
            @PathVariable Long id
    ) {
        listaService.deletarLista(id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}/filmes")
    @Operation(
            summary = "Remove filmes de uma lista",
            description = "Remove um ou mais filmes da lista informada."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Filmes removidos da lista com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Lista ou filme não encontrado"
            )
    })
    public ResponseEntity<ListaResponseDTO> removerFilmesDaLista(

            @Parameter(
                    description = "ID da lista da qual os filmes serão removidos",
                    example = "1"
            )
            @PathVariable Long id,

            @RequestBody List<Long> filmes
    ) {
        ListaResponseDTO listaResponseDTO =
                listaService.deletarFilmesDaLista(id, filmes);

        return ResponseEntity.ok(listaResponseDTO);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza uma lista",
            description = "Atualiza os dados de uma lista existente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista atualizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Lista não encontrada"
            )
    })
    public ResponseEntity<ListaResponseDTO> atualizar(

            @Parameter(
                    description = "ID da lista que será atualizada",
                    example = "1"
            )
            @PathVariable Long id,

            @RequestBody @Valid ListaAtualizarDTO listaAtualizarDTO
    ) {
        ListaResponseDTO listaResponseDTO =
                listaService.atualizarLista(id, listaAtualizarDTO);

        return ResponseEntity.ok(listaResponseDTO);
    }
}