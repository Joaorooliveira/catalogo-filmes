package dev.joaorooliveira.catalogo_filmes.domain.lista;

import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ListaRepository extends JpaRepository<Lista, Long> , JpaSpecificationExecutor<Lista> {
    Page<Lista> findByTituloContainingIgnoreCase(
            String titulo,
            Pageable pageable
    );
}
