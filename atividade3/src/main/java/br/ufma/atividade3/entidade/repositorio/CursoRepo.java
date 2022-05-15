package br.ufma.atividade3.entidade.repositorio;

import br.ufma.atividade3.entidade.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepo extends JpaRepository<Curso,Long> {
    void deleteByNome(String nome);
    boolean existsByNome(String nome);
    List<Curso> findByNome(String nome);
}
