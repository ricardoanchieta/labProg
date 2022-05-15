package br;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import br.ufma.atividade3.entidade.FaixaSalario;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class FaixaSalarioRepositoryTest {

    @Autowired
    FaixaSalarioRepositoryTest repository;
    
    @Test
    public void deveSalvarFaixaSalario() {
      
      //cenário
      FaixaSalario faixaSalario = FaixaSalario.builder().descricao("vinte mil reis").build();
      //ação
      FaixaSalario retorno = repository.save(faixaSalario);
      
      //verificação
      Assertions.assertNotNull(retorno);
      Assertions.assertEquals(faixaSalario.getDescricao(), retorno.getDescricao());

      //rollback
      repository.delete(retorno);
    }

}