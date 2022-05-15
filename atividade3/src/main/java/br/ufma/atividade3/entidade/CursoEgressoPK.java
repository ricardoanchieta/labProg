package br.ufma.atividade3.entidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CursoEgressoPK implements Serializable {

    @Column(name="egresso_id")
    private Long egresso;

    @Column(name="curso_id")
    private Long curso;
}
