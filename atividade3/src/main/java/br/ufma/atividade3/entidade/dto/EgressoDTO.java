package br.ufma.atividade3.entidade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EgressoDTO {
    private String nome;
    private String email;
    private String cpf;
    private String resumo;
    private String url_foto;
}
