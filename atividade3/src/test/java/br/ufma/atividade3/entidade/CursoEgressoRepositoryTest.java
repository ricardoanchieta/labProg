package br.ufma.atividade3.entidade;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import br.ufma.atividade3.entidade.repositorio.CursoEgressoRepo;
import br.ufma.atividade3.entidade.repositorio.CursoRepo;
import br.ufma.atividade3.entidade.repositorio.EgressoRepo;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class CursoEgressoRepositoryTest {
    
    @Autowired
    CursoEgressoRepo repository;
    @Autowired
    CursoRepo repositoryCurso;
    @Autowired
    EgressoRepo repositoryEgresso;


    private Curso cenarioCurso(){
        return Curso.builder()
                        .nome("ciencia da computacao")
                        .nivel("superior")
                        .build();
    }

    
    private Egresso cenarioEgresso(){
        return Egresso.builder().nome("Ricardo")
            .email("ric@oi.com")
            .cpf("2222")
            .resumo("Egresso de CP")
            .url_foto("www.foto.com")
            .build();
    }

    private CursoEgresso cenarioCursoEgresso(Curso c, Egresso e){
        return CursoEgresso.builder()
                        .id(new CursoEgressoId(e.getId(), c.getId()))
                        .curso(c)
                        .egresso(e)
                        .dataConclusao(LocalDate.of(2022,Month.MAY, 15))
                        .dataInicio(LocalDate.of(2020,Month.MAY, 15))
                        .build();
    }


    @Test
    public void deveSalvarCursoEgresso() {
        // cenario
        Curso curso = cenarioCurso();
        Egresso egresso = cenarioEgresso();
        curso = repositoryCurso.save(curso);
        egresso = repositoryEgresso.save(egresso);
        
        CursoEgresso cursoEgresso = cenarioCursoEgresso(curso,egresso);

        // ação
        CursoEgresso salvo = repository.save(cursoEgresso);
        
        // verificação
        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(cursoEgresso.getDataConclusao(), salvo.getDataConclusao());
        Assertions.assertEquals(cursoEgresso.getDataInicio(), salvo.getDataInicio());
        Assertions.assertEquals(cursoEgresso.getEgresso().getId(), salvo.getEgresso().getId());
        Assertions.assertEquals(cursoEgresso.getCurso().getId(), salvo.getCurso().getId());
    }

    @Test
    public void deveBuscarCursoEgressoPorEgresso() {
        // cenario
        Curso curso = cenarioCurso();
        Egresso egresso = cenarioEgresso();
        curso = repositoryCurso.save(curso);
        egresso = repositoryEgresso.save(egresso);
        
        CursoEgresso cursoEgresso = cenarioCursoEgresso(curso,egresso);
        CursoEgresso salvo = repository.save(cursoEgresso);

        // ação
        List<CursoEgresso> retList = repository.findByEgresso(salvo.getEgresso());
        CursoEgresso ret = retList.get(0);
        
        // verificação
        Assertions.assertEquals(cursoEgresso.getDataConclusao(), ret.getDataConclusao());
        Assertions.assertEquals(cursoEgresso.getDataInicio(), ret.getDataInicio());
        Assertions.assertEquals(cursoEgresso.getEgresso().getId(), ret.getEgresso().getId());
        Assertions.assertEquals(cursoEgresso.getCurso().getId(), ret.getCurso().getId());
    }

    @Test
    public void deveBuscarCursoEgressoPorCurso() {
        // cenario
        Curso curso = cenarioCurso();
        Egresso egresso = cenarioEgresso();
        curso = repositoryCurso.save(curso);
        egresso = repositoryEgresso.save(egresso);
        
        CursoEgresso cursoEgresso = cenarioCursoEgresso(curso,egresso);
        CursoEgresso salvo = repository.save(cursoEgresso);

        // ação
        List<CursoEgresso> retList = repository.findByCurso(salvo.getCurso());
        CursoEgresso ret = retList.get(0);
        
        // verificação

        Assertions.assertEquals(cursoEgresso.getDataConclusao(), ret.getDataConclusao());
        Assertions.assertEquals(cursoEgresso.getDataInicio(), ret.getDataInicio());
        Assertions.assertEquals(cursoEgresso.getEgresso().getId(), ret.getEgresso().getId());
        Assertions.assertEquals(cursoEgresso.getCurso().getId(), ret.getCurso().getId());
    }

    @Test
    public void deveVerificarSeExisteCursoEgressoPorEgresso() {
        // cenario
        Curso curso = cenarioCurso();
        Egresso egresso = cenarioEgresso();
        curso = repositoryCurso.save(curso);
        egresso = repositoryEgresso.save(egresso);
        
        CursoEgresso cursoEgresso = cenarioCursoEgresso(curso,egresso);
        CursoEgresso salvo = repository.save(cursoEgresso);

        // ação
        boolean ret = repository.existsByEgresso(salvo.getEgresso());
        
        // verificação
        Assertions.assertTrue(ret);
    }

    @Test
    public void deveVerificarSeExisteCursoEgressoPorCurso() {
        // cenario
        Curso curso = cenarioCurso();
        Egresso egresso = cenarioEgresso();
        curso = repositoryCurso.save(curso);
        egresso = repositoryEgresso.save(egresso);
        
        CursoEgresso cursoEgresso = cenarioCursoEgresso(curso,egresso);
        CursoEgresso salvo = repository.save(cursoEgresso);

        // ação
        boolean ret = repository.existsByCurso(salvo.getCurso());
        
        // verificação
        Assertions.assertTrue(ret);
    }

    @Test
    public void deveDeletarCursoEgressoPorEgresso() {
        // cenario
        Curso curso = cenarioCurso();
        Egresso egresso = cenarioEgresso();
        curso = repositoryCurso.save(curso);
        egresso = repositoryEgresso.save(egresso);
        
        CursoEgresso cursoEgresso = cenarioCursoEgresso(curso,egresso);
        CursoEgresso salvo = repository.save(cursoEgresso);

        // ação
        repository.deleteByEgresso(salvo.getEgresso());
        boolean ret = repository.existsByEgresso(salvo.getEgresso());
        
        // verificação
        Assertions.assertFalse(ret);
    }


}