package br.ufma.atividade3.entidade;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import br.ufma.atividade3.entidade.repositorio.CargoRepo;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class CargoRepositoryTest {

    @Autowired
    CargoRepo repository;
    
    @Test
    public void deveSalvarCargo(){
      
      //cenário
      Cargo cargo = Cargo.builder()
          .nome("chefe")
          .descricao("descricao")
          .build();

      //ação
      //  
      Cargo salvo = repository.save(cargo); 
      
      //verificação
      Assertions.assertNotNull(salvo);
      Assertions.assertEquals(cargo.getNome(), salvo.getNome());
      Assertions.assertEquals(cargo.getDescricao(), salvo.getDescricao());

      // repository.delete(salvo);
    }

    @Test
    public void deveBuscarCargoPorNome() {
      
      //cenário
      Cargo cargo = Cargo.builder()
        .nome("chefe")
        .descricao("descricao")
        .build();

      //ação
      //  
      Cargo salvo = repository.save(cargo); 
      List <Cargo>  retList = repository.findByNome(cargo.getNome());
      
      //verificação
      Assertions.assertFalse(retList.isEmpty());

      Cargo ret = retList.get(0);

      Assertions.assertEquals(cargo.getNome(), ret.getNome());
      Assertions.assertEquals(cargo.getDescricao(), ret.getDescricao());

      //rollback
      repository.delete(salvo);
    }

    @Test
    public void deveVerificarSeExisteCargo() {
      
      //cenário
      Cargo cargo = Cargo.builder()
        .nome("chefe")
        .descricao("descricao")
        .build();

      //ação
      Cargo salvo = repository.save(cargo); 
      boolean ret = repository.existsByNome(cargo.getNome());ß
      
      //verificação
      Assertions.assertTrue(ret);

      //rollback
      repository.delete(salvo);
    }

}