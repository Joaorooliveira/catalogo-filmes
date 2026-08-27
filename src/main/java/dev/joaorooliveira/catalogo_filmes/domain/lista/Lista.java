package dev.joaorooliveira.catalogo_filmes.domain.lista;

import dev.joaorooliveira.catalogo_filmes.domain.filme.Filme;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "lista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Lista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @CreatedDate
    @Column(nullable = false, name = "criado_em")
    private LocalDateTime criadoEm;

    @LastModifiedDate
    @Column(nullable = false, name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "lista_filme",
            joinColumns = @JoinColumn(name = "lista_id"),
            inverseJoinColumns = @JoinColumn(name = "filme_id")
    )
    private List<Filme> filmes;
}
