package br.ufma.atividade3.entidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="curso_egresso")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CursoEgresso {
    @EmbeddedId
    CursoEgressoPK id;

    @ManyToOne
    @MapsId("egressoId")
    @JoinColumn(name="egresso_id")
    private Egresso egresso;

    @ManyToOne
    @MapsId("cursoId")
    @JoinColumn(name="curso_id")
    private Curso curso;

    @JoinColumn(name="data_inicio")
    private LocalDate dataInicio;

    @JoinColumn(name="data_conclusao")
    private LocalDate dataConclusao;
}
