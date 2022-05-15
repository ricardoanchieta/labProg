package br.ufma.atividade3.entidade.repositorio;

import br.ufma.atividade3.entidade.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CargoRepo extends JpaRepository<Cargo, Long> {

    List<Cargo> findByNome(String nome);
    boolean existsByNome(String nome);

}
