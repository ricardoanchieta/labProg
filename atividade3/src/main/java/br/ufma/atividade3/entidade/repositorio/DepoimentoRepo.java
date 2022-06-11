package br.ufma.atividade3.entidade.repositorio;

import br.ufma.atividade3.entidade.Depoimento;
import br.ufma.atividade3.entidade.Egresso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepoimentoRepo extends JpaRepository<Depoimento, Long> {
    List<Depoimento> findByEgresso(Egresso egresso);
    boolean existsByEgresso(Egresso egresso);
    void deleteByEgresso(Egresso egresso);
}
