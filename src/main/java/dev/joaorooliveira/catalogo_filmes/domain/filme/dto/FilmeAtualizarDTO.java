package dev.joaorooliveira.catalogo_filmes.domain.filme.dto;

import dev.joaorooliveira.catalogo_filmes.domain.filme.Filme;
import dev.joaorooliveira.catalogo_filmes.domain.filme.enums.GeneroTipo;

import java.time.LocalDate;

public record FilmeAtualizarDTO(
        String nome,
        String descricao,
        GeneroTipo genero,
        LocalDate anoLancamento,
        Integer avaliacao,
        Boolean assistido,
        Boolean favorito,
        Long diretorId

) {
    public void preencher(Filme filme) {
        if(this.nome!=null){
            filme.setNome(this.nome);
        }
        if(this.descricao!=null){
            filme.setDescricao(this.descricao);
        }
        if(this.genero!=null){
            filme.setGenero(this.genero);
        }
        if(this.anoLancamento!=null){
            filme.setAnoLancamento(this.anoLancamento);

        }
        if(this.avaliacao!=null){
            filme.setAvaliacao(this.avaliacao);
        }
        if(this.assistido!=null){
            filme.setAssistido(this.assistido);
        }
        if(this.favorito!=null){
            filme.setFavorito(this.favorito);
        }

    }

}
