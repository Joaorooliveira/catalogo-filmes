package dev.joaorooliveira.catalogo_filmes.domain.filme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FilmeRepository extends JpaRepository<Filme, Long> , JpaSpecificationExecutor<Filme> {
}
