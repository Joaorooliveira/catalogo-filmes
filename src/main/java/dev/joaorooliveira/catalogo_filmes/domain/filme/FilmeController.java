package dev.joaorooliveira.catalogo_filmes.domain.filme;

import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeAtualizarDTO;
import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeFiltroRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Filmes",
        description = "Endpoints para gerenciamento de filmes"
)
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping
    @Operation(
            summary = "Cadastra um novo filme",
            description = "Cadastra um filme no catálogo."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Filme cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            )
    })
    public ResponseEntity<FilmeResponseDTO> salvar(
            @RequestBody @Valid FilmeRequestDTO filmeRequestDTO
    ) {
        FilmeResponseDTO filmeResponseDTO =
                filmeService.salvarFilme(filmeRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(filmeResponseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(filmeResponseDTO);
    }


    @GetMapping
    @Operation(
            summary = "Busca filmes",
            description = "Retorna uma lista paginada de filmes, permitindo a aplicação de filtros."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Filmes encontrados com sucesso"
            )
    })
    public ResponseEntity<Page<FilmeResponseDTO>> buscar(
            @PageableDefault(size = 10) Pageable pageable,
            FilmeFiltroRequestDTO filtro
    ) {
        return ResponseEntity.ok(
                filmeService.buscarFilmes(pageable, filtro)
        );
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Busca um filme pelo ID",
            description = "Retorna os dados de um filme específico a partir do seu ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Filme encontrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Filme não encontrado"
            )
    })
    public ResponseEntity<FilmeResponseDTO> buscarFilmePorId(
            @Parameter(
                    description = "ID do filme",
                    example = "1"
            )
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                filmeService.buscarFilmePorId(id)
        );
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza um filme",
            description = "Atualiza os dados de um filme existente a partir do seu ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Filme atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Filme não encontrado"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            )
    })
    public ResponseEntity<FilmeResponseDTO> atualizar(
            @Parameter(
                    description = "ID do filme que será atualizado",
                    example = "1"
            )
            @PathVariable Long id,

            @RequestBody FilmeAtualizarDTO filmeAtualizarDTO
    ) {
        return ResponseEntity.ok(
                filmeService.atualizarFilme(
                        id,
                        filmeAtualizarDTO
                )
        );
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remove um filme",
            description = "Remove um filme do catálogo a partir do seu ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Filme removido com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Filme não encontrado"
            )
    })
    public ResponseEntity<Void> deletar(
            @Parameter(
                    description = "ID do filme que será removido",
                    example = "1"
            )
            @PathVariable Long id
    ) {
        filmeService.deletarFilme(id);
        return ResponseEntity.ok().build();
    }
}