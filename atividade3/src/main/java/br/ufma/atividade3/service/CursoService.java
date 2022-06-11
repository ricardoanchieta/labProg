package br.ufma.atividade3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufma.atividade3.entidade.Curso;
import br.ufma.atividade3.entidade.Egresso;
import br.ufma.atividade3.entidade.repositorio.CursoRepo;
import br.ufma.atividade3.service.exceptions.ErroCargoRunTime;
import br.ufma.atividade3.service.exceptions.ErroCursoRunTime;

@Service
public class CursoService {
  @Autowired
  CursoRepo repository;

  public Curso salvarCurso(Curso curso) {
    verificarCurso(curso);
    return repository.save(curso);
  }


  public List<Egresso> getEgressos(Curso curso) {
    return repository.consultarEgressos(curso.getId());
  }

  public Long getQuantitativoEgressos(Curso curso) {
    return repository.quantitativoEgressos(curso.getId());
  }

  private void verificarId(Curso curso) {
    if ((curso == null) || (curso.getId() == null))
      throw new ErroCursoRunTime("Curso inválido");
  }

  private void verificarCurso(Curso curso) {
    verificarId(curso);
    if ((curso.getNome() == null) || (curso.getNome().equals("")))
      throw new ErroCargoRunTime("Nome não pode ser vazio");
  }
}
