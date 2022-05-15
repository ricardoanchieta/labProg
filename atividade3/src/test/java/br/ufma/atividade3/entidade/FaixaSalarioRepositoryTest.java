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
@SpringBootTes
public class FaixaSalarioRepositoryTest {

    @Autowired
    FaixaSalarioRepositoryTest repository;

    private FaixaSalario createCenario(){
      FaixaSalario faixaSalario = FaixaSalario.builder().descricao("vinte mil reis").build();

      return faixaSalario;
    }
    
    @Test
    public void deveVerificarSalvarFaixaSalario() throws Exception {
      
      //cenário
      FaixaSalario faixaSalario = this.createCenario();

      //ação
      FaixaSalario retorno = repository.save(faixaSalario);
      
      //verificação
      Assertions.assertNotNull(retorno);
      Assertions.assertEquals(faixaSalario.getDescricao(), retorno.getDescricao());

      //rollback
      repository.delete(retorno);
    }

}