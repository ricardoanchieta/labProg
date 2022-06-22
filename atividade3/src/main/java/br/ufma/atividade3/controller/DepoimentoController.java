package br.ufma.atividade3.controller;

import br.ufma.atividade3.entidade.Depoimento;
import br.ufma.atividade3.entidade.Egresso;
import br.ufma.atividade3.entidade.dto.DepoimentoDTO;
import br.ufma.atividade3.entidade.dto.EgressoDTO;
import br.ufma.atividade3.entidade.repositorio.DepoimentoRepo;
import br.ufma.atividade3.entidade.repositorio.EgressoRepo;
import br.ufma.atividade3.service.DepoimentoService;
import br.ufma.atividade3.service.exceptions.ErroDepoimentoRunTime;
import br.ufma.atividade3.service.exceptions.ErroEgressoRunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/depoimento")

public class DepoimentoController {

    @Autowired
    DepoimentoService service;

    @Autowired
    EgressoRepo egressoRepo;

    @PostMapping("/salvar")
    public ResponseEntity salvarDepoimento(@RequestBody DepoimentoDTO request){
        Egresso egresso = egressoRepo.getById(request.getId_egresso());

        Depoimento depoimento = Depoimento.builder()
                .egresso(egresso)
                .texto(request.getTexto())
                .data(request.getData())
                .build();

        try{
            Depoimento salvo = service.salvarDepoimento(depoimento);
            return new ResponseEntity(salvo, HttpStatus.CREATED);
        }catch (ErroDepoimentoRunTime ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity deleteDepoimento(@RequestBody DepoimentoDTO request){
        Long id = request.getId();

        try{
            service.deletarDepoimento(id);
            return new ResponseEntity("Depoimento deletado com sucesso",HttpStatus.ACCEPTED);
        }catch (ErroEgressoRunTime ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/listar")
    public ResponseEntity listaDepoimentoPorEgresso(@RequestBody DepoimentoDTO request) {
        Long id_egresso = request.getId_egresso();

        Egresso egresso = egressoRepo.getById(id_egresso);

        try{
            List<Depoimento> depoimentos = service.depoimentosPorEgresso(egresso);
            return new ResponseEntity(depoimentos,HttpStatus.ACCEPTED);
        }catch (ErroEgressoRunTime ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
