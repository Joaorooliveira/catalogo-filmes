package dev.joaorooliveira.catalogo_filmes.domain.diretor;

import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorAtualizarDTO;
import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorFiltroRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/diretores")
@Tag(
        name = "Diretores",
        description = "Endpoints para gerenciamento de diretores"
)
public class DiretorController {

    private final DiretorService diretorService;

    public DiretorController(DiretorService diretorService) {
        this.diretorService = diretorService;
    }

    @PostMapping
    @Operation(
            summary = "Cadastra um novo diretor",
            description = "Cadastra um diretor no catálogo."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Diretor cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            )
    })
    public ResponseEntity<DiretorResponseDTO> salvar(
            @RequestBody @Valid DiretorRequestDTO diretorRequestDTO
    ) {
        DiretorResponseDTO diretorResponseDTO =
                diretorService.salvarDiretor(diretorRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(diretorResponseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(diretorResponseDTO);
    }


    @GetMapping
    @Operation(
            summary = "Busca diretores",
            description = "Retorna uma lista paginada de diretores, permitindo a aplicação de filtros."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Diretores encontrados com sucesso"
            )
    })
    public ResponseEntity<Page<DiretorResponseDTO>> buscar(
            @PageableDefault(size = 10) Pageable pageable,
            DiretorFiltroRequestDTO filtro
    ) {
        return ResponseEntity.ok(
                diretorService.buscarDiretores(pageable, filtro)
        );
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Busca um diretor pelo ID",
            description = "Retorna os dados de um diretor específico a partir do seu ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Diretor encontrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Diretor não encontrado"
            )
    })
    public ResponseEntity<DiretorResponseDTO> buscarPorId(

            @Parameter(
                    description = "ID do diretor",
                    example = "1"
            )
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                diretorService.buscarDiretorPorId(id)
        );
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza um diretor",
            description = "Atualiza os dados de um diretor existente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Diretor atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Diretor não encontrado"
            )
    })
    public ResponseEntity<DiretorResponseDTO> atualizar(

            @Parameter(
                    description = "ID do diretor que será atualizado",
                    example = "1"
            )
            @PathVariable Long id,

            @RequestBody DiretorAtualizarDTO diretorAtualizarDTO
    ) {
        return ResponseEntity.ok(
                diretorService.atualizarDiretor(
                        id,
                        diretorAtualizarDTO
                )
        );
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remove um diretor",
            description = "Remove um diretor do catálogo a partir do seu ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Diretor removido com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Diretor não encontrado"
            )
    })
    public ResponseEntity<Void> deletar(

            @Parameter(
                    description = "ID do diretor que será removido",
                    example = "1"
            )
            @PathVariable Long id
    ) {
        diretorService.deletarDiretor(id);

        return ResponseEntity.noContent().build();
    }
}