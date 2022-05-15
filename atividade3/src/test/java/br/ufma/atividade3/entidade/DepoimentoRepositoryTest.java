package br.ufma.atividade3.entidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufma.atividade3.entidade.repositorio.DepoimentoRepo;
import br.ufma.atividade3.entidade.repositorio.EgressoRepo;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class DepoimentoRepositoryTest {

    @Autowired
    DepoimentoRepo repository;
    @Autowired
    EgressoRepo repositoryEgresso;

    @Test
    public void deveSalvarDepoimento() {
      
      //cenário
      Depoimento entity = Depoimento.builder()
      .texto("texto")
      .data(12/05/2020)
      .build();

      //ação
      Depoimento retorno = repository.save(entity);
      
      //verificação
      Assertions.assertNotNull(retorno);
      Assertions.assertEquals(entity.getTexto(), retorno.getTexto());

      //rollback
      repository.delete(retorno);
    }

    @Test
    public void deveBuscarDepoimentoPorEgresso() {
      
      //cenário
      Depoimento depoimento = Depoimento.builder()
        .texto("texto")
        .data(12/05/2020)
        .build();
;
      Egresso egresso = Egresso.builder()
        .nome("Nome  ")
        .email("nome@exemplo.com")
        .cpf("12345678910")
        .resumo("resumo")
        .urlFoto("http://")
        .build();
      egresso = repositoryEgresso.save(egresso);
      depoimento.setEgresso(egresso);

      //ação
      Depoimento retorno = repository.save(depoimento);
      
      //verificação
      Assertions.assertNotNull(retorno);
      Assertions.assertEquals(depoimento.getTexto(), retorno.getTexto());
      Assertions.assertEquals(depoimento.getData(), retorno.getData());
      Assertions.assertEquals(depoimento.getEgresso().getId(), retorno.getEgresso().getId());

      //rollback
      repositoryEgresso.delete(retorno);
    }

    @Test
    public void deveDeletarDepoimentoPorEgresso() {
      
      //cenário
      Depoimento depoimento = Depoimento.builder()
        .texto("texto")
        .data(12/05/2020)
        .build();
      Egresso egresso = Egresso.builder()
        .nome("Nome  ")
        .email("nome@exemplo.com")
        .cpf("12345678910")
        .resumo("resumo")
        .urlFoto("http://")
        .build();
      egresso = repositoryEgresso.save(egresso);
      depoimento.setEgresso(egresso);
      
      //ação
      repository.save(depoimento);

      repository.deleteByEgresso(egresso);  
      boolean ret = repository.existsByEgresso(egresso);  
      
      //verificação
      Assertions.assertFalse(ret);
    }

}