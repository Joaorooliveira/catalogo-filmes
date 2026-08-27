package dev.joaorooliveira.catalogo_filmes.domain.lista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ListaRepository extends JpaRepository<Lista, Long> , JpaSpecificationExecutor<Lista> {
}
