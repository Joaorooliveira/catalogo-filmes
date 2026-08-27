package dev.joaorooliveira.catalogo_filmes.domain.diretor.dto;

import dev.joaorooliveira.catalogo_filmes.domain.diretor.Diretor;

import java.time.LocalDate;

public record DiretorAtualizarDTO(
        String nome,
        LocalDate dataNascimento
) {

    public void preencher(Diretor diretor){
        if(this.dataNascimento!= null){
            diretor.setDataNascimento(this.dataNascimento);
        }
        if(this.nome != null){
            diretor.setNome(this.nome);
        }
    }
}
