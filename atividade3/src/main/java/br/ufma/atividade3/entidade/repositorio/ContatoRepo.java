package br.ufma.atividade3.entidade.repositorio;

import br.ufma.atividade3.entidade.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContatoRepo extends JpaRepository<Contato, Long> {

    List<Contato> findByNome(String nome);
    boolean existsByNome(String nome);
}
