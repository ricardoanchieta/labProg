package br.ufma.atividade3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufma.atividade3.entidade.FaixaSalario;
import br.ufma.atividade3.entidade.repositorio.FaixaSalarioRepo;

@Service
public class FaixaSalarioService {
  
  @Autowired
  FaixaSalarioRepo repository;

  public Long getQuantitativoEgresso(FaixaSalario faixaSalario) {
    return repository.getQuantitativoEgresso(faixaSalario.getId());
  }
}
