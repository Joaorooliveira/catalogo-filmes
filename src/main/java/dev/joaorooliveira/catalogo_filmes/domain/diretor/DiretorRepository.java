package dev.joaorooliveira.catalogo_filmes.domain.diretor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiretorRepository extends JpaRepository<Diretor, Long> , JpaSpecificationExecutor<Diretor> {
}
