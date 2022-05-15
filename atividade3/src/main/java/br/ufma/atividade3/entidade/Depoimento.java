package br.ufma.atividade3.entidade;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "depoimento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Depoimento {
    @Id
    @Column(name="id_depoimento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="egresso_id")
    private Long egresso_id;

    @Column(name="texto")
    private String texto;

    @Column(name="data")
    private Date data;
}
