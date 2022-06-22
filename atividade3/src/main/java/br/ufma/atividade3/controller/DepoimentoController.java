package br.ufma.atividade3.controller;

import br.ufma.atividade3.entidade.Depoimento;
import br.ufma.atividade3.entidade.Egresso;
import br.ufma.atividade3.entidade.dto.DepoimentoDTO;
import br.ufma.atividade3.entidade.repositorio.DepoimentoRepo;
import br.ufma.atividade3.entidade.repositorio.EgressoRepo;
import br.ufma.atividade3.service.DepoimentoService;
import br.ufma.atividade3.service.exceptions.ErroDepoimentoRunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/api/depoimento")

public class DepoimentoController {

    @Autowired
    DepoimentoService service;

    @Autowired
    EgressoRepo egressoRepo;

    @PostMapping("salvar")
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


}
