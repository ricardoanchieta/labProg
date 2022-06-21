package br.ufma.atividade3.entidade;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufma.atividade3.entidade.repositorio.FaixaSalarioRepo;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FaixaSalarioRepositoryTest {

    @Autowired
    FaixaSalarioRepo repository;
    
    @Test
    public void deveSalvarFaixaSalario() {
      
      //cenário
      FaixaSalario novafaixaSalario = FaixaSalario.builder().descricao("vinte mil reis").build();
      //ação
      FaixaSalario retorno = repository.save(novafaixaSalario);
      
      //verificação
      Assertions.assertNotNull(retorno);
      Assertions.assertEquals(novafaixaSalario.getDescricao(), retorno.getDescricao());

      //rollback
      //repository.delete(retorno);
    }



}