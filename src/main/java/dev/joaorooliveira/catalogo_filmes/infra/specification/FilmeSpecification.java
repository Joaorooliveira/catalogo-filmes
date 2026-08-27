package dev.joaorooliveira.catalogo_filmes.infra.specification;

import dev.joaorooliveira.catalogo_filmes.domain.filme.Filme;
import dev.joaorooliveira.catalogo_filmes.domain.filme.dto.FilmeFiltroRequestDTO;
import dev.joaorooliveira.catalogo_filmes.domain.filme.enums.GeneroTipo;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FilmeSpecification {

    public static Specification<Filme> comFiltros(FilmeFiltroRequestDTO filtro){
        return Specification
                .where(nomeContem(filtro.nome()))
                .and(descricaoContem(filtro.descricao()))
                .and(generoIgual(filtro.genero()))
                .and(anoLancamentoIgual(filtro.anoLancamento()))
                .and(avaliacaoIgual(filtro.avaliacao()))
                .and(assistidoIgual(filtro.assistido()))
                .and(favoritoIgual(filtro.favorito()))
                .and(diretorIgual(filtro.diretorId()));

    }

    private static Specification<Filme> favoritoIgual(Boolean favorito) {
        return (root,query,cb) -> {
            if(favorito == null){
                return null;
            }
            return cb.equal(root.get("favorito"), favorito);
        };
    }

    private static Specification<Filme> assistidoIgual(Boolean assistido) {
        return (root, query, cb) -> {
            if(assistido == null){
                return null;
            }
          return cb.equal(root.get("assistido"),assistido);
        };
    }

    private static Specification<Filme> avaliacaoIgual(Integer avaliacao) {
        return (root,query,cb) ->{
            if(avaliacao == null){
                return null;
            }
            return cb.equal(root.get("avaliacao"), avaliacao);
        };
    }

    private static Specification<Filme> anoLancamentoIgual(LocalDate anoLancamento) {
        return (root,query,cb) -> {
            if(anoLancamento == null){
                return null;
            }
            return cb.equal(root.get("anoLancamento"), anoLancamento);
        };
    }

    private static Specification<Filme> generoIgual(GeneroTipo genero) {
        return (root,query,cb) -> {
            if (genero == null){
                return null;
            }
            return cb.equal(root.get("genero"), genero);
        };
    }

    private static Specification<Filme> descricaoContem(String descricao) {
        return (root, query, cb) -> {
            if (descricao == null || descricao.isBlank()) {
                return null;
            }
            return cb.like(cb.lower(root.get("descricao")), "%" + descricao.toLowerCase() + "%");
        };
    }

    private static Specification<Filme> nomeContem(String nome) {
        return (root, query, cb) -> {
            if (nome == null || nome.isBlank()) {
                return null;
            }
            return cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }

    private static Specification<Filme> diretorIgual(Long diretorId) {
        return (root, query, cb) -> {
            if (diretorId == null) {
                return null;
            }

            return cb.equal(
                    root.get("diretor").get("id"),
                    diretorId
            );
        };
    }


}
