package dev.joaorooliveira.catalogo_filmes.infra.specification;

import dev.joaorooliveira.catalogo_filmes.domain.diretor.Diretor;
import dev.joaorooliveira.catalogo_filmes.domain.diretor.dto.DiretorFiltroRequestDTO;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class DiretorSpecification {

    public static Specification<Diretor> comFiltros(DiretorFiltroRequestDTO filtro){
        return Specification
                .where(nomeContem(filtro.nome()))
                .and(dataNascimentoIgual(filtro.dataNascimento()));
    }

    private static Specification<Diretor> nomeContem(String nome) {
        return (root,query,cb)-> {
            if(nome== null){
                return null;
            }
            return cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }

    private static Specification<Diretor> dataNascimentoIgual(LocalDate dataNascimento) {
        return (root,query,cb) ->{
            if(dataNascimento == null){
                return null;
            }
            return cb.equal(root.get("dataNascimento"), dataNascimento);
        };
    }

}
