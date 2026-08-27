package dev.joaorooliveira.catalogo_filmes.domain.diretor;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
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
@Table(name = "diretor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Diretor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 255)
    private String nome;

    @Past
    private LocalDate dataNascimento;

    @CreatedDate
    @Column(nullable = false,name = "criado_em")
    private LocalDateTime criadoEm;

    @LastModifiedDate
    @Column(nullable = false,name = "atualizado_em")
    private LocalDateTime atualizadoEm;
}
