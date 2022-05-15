package br.ufma.atividade3.entidade;

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
@Table(name = "faixa_salario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FaixaSalario {

    @Id
    @Column(name="id_faixa_salario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="descricao")
    private String descricao;
}
