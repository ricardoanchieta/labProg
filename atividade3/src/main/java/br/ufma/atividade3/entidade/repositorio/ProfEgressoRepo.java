package br.ufma.atividade3.entidade.repositorio;

import br.ufma.atividade3.entidade.Egresso;
import br.ufma.atividade3.entidade.ProfEgresso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfEgressoRepo extends JpaRepository<ProfEgresso,Long> {
    List<ProfEgresso> findByEgresso(Egresso egresso);
    boolean existsByEgresso(Egresso egresso);
    void deleteByEgresso(Egresso egresso);
}
