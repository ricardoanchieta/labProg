package br.ufma.atividade3.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufma.atividade3.entidade.Depoimento;
import br.ufma.atividade3.entidade.Egresso;
import br.ufma.atividade3.entidade.repositorio.DepoimentoRepo;
import br.ufma.atividade3.service.exceptions.ErroDepoimentoRunTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DepoimentoServiceTest {

  @Autowired
  DepoimentoService service;

  @Autowired
  DepoimentoRepo repository;
  
  @Test
  public void deveGerarErroAoTentarSalvarSemTexto() {
    Egresso egresso = Egresso.builder().nome("Pedro").cpf("000").build();
    Depoimento depoimento = Depoimento.builder()
                            .data(new java.sql.Date(0)).texto("").egresso(egresso).build();
    Assertions.assertThrows(ErroDepoimentoRunTime.class,
                            () -> service.salvarDepoimento(depoimento),
                            "Texto não pode ser vazio");
    repository.delete(depoimento);
  }

  @Test
  public void deveGerarErroAoTentarSalvarSemEgresso() {
    Depoimento depoimento = Depoimento.builder()
                            .data(new java.sql.Date(0)).texto("Texto").egresso(null).build();
    Assertions.assertThrows(ErroDepoimentoRunTime.class,
                            () -> service.salvarDepoimento(depoimento),
                            "Texto não pode ser vazio");
    repository.delete(depoimento);
  }

  @Test
  public void testaEdicaoDeDepoimento() {
    Assertions.assertThrows(ErroDepoimentoRunTime.class,
                            () -> service.editarDepoimento(-1l, "texto"),
                            "Depoimento inexistente");
    Assertions.assertThrows(ErroDepoimentoRunTime.class,
                            () -> service.editarDepoimento(0l, ""),
                            "Texto inválido");
  }

  @Test
  public void testaDeletarDepoimento() {
    Assertions.assertThrows(ErroDepoimentoRunTime.class,
                            () -> service.deletarDepoimento(-1l),
                            "Depoimento inexistente");
  }

  @Test
  public void testaDepoimentosRecentes() {
    List<Depoimento> depoimentos = service.depoimentosRecentes();
    if (depoimentos.size() >= 2) {
      Assertions.assertEquals(
        depoimentos.get(0).getData().compareTo(depoimentos.get(1).getData()) < 0,
        true);
    }
  }
}
