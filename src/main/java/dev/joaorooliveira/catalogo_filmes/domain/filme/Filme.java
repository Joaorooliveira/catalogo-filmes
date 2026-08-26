package dev.joaorooliveira.catalogo_filmes.domain.filme;

import dev.joaorooliveira.catalogo_filmes.domain.diretor.Diretor;
import dev.joaorooliveira.catalogo_filmes.domain.filme.enums.GeneroTipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "filme")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255,nullable = false)
    private String nome;

    private String descricao;

    @Column(nullable = false,length = 50)
    @Enumerated(EnumType.STRING)
    private GeneroTipo genero;

    @Column(name = "ano_lancamento")
    private LocalDate anoLancamento;

    private Integer avaliacao;

    @Column(nullable = false)
    private boolean assistido = false;

    @Column(nullable = false)
    private boolean favorito = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diretor_id")
    private Diretor diretor;

    @CreatedDate
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @LastModifiedDate
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;



}
