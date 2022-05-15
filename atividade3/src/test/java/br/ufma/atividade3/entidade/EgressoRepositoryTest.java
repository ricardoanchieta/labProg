package br.ufma.atividade3.entidade;

import br.ufma.atividade3.entidade.repositorio.EgressoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EgressoRepositoryTest {
    @Autowired
    EgressoRepo repo;

    @Test
    public void deveVerificarSalvarEgresso(){
        //cenario
        Egresso novo = Egresso.builder().nome("Ricardo")
                                        .email("ric@oi.com")
                                        .cpf("2222")
                                        .resumo("Egresso de CP")
                                        .url_foto("www.foto.com")
                                        .build();

        //acao

        Egresso retorno = repo.save(novo);

        //varificacao
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(novo.getNome(), retorno.getNome());
        Assertions.assertEquals(novo.getEmail(), retorno.getEmail());
        Assertions.assertEquals(novo.getCpf(), retorno.getCpf());
        Assertions.assertEquals(novo.getResumo(), retorno.getResumo());
        Assertions.assertEquals(novo.getUrl_foto(), retorno.getUrl_foto());

        //rollback
        repo.delete(retorno);
        }

    @Test
    public void deveRemoverEgresso(){
        //cenario
        Egresso novo = Egresso.builder().nome("Ricardo")
                                        .email("ric@oi.com")
                                        .cpf("2222")
                                        .resumo("Egresso de CP")
                                        .url_foto("www.foto.com")
                                        .build();
        Egresso retorno = repo.save(novo);

        //acao
        Long id = retorno.getId();
        repo.deleteById(id);

        //verificacao
        Optional<Egresso> temp = repo.findById(id);
        Assertions.assertFalse((temp.isPresent()));
    }

    @Test
    public void deveProcurarEgresso(){
        //cenario
        Egresso novo = Egresso.builder().nome("Ricardo")
                                        .email("ric@oi.com")
                                        .cpf("2222")
                                        .resumo("Egresso de CP")
                                        .url_foto("www.foto.com")
                                        .build();
        Egresso retorno = repo.save(novo);

        //acao
        Optional<Egresso> temp = repo.findById(retorno.getId());

        //verificacao
        Assertions.assertTrue(temp.isPresent());

        //rollback
        repo.delete(retorno);
    }

}
