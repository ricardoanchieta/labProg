package br.ufma.atividade3.controller;

import br.ufma.atividade3.entidade.Egresso;
import br.ufma.atividade3.entidade.dto.EgressoDTO;
import br.ufma.atividade3.entidade.repositorio.EgressoRepo;
import br.ufma.atividade3.service.EgressoService;
import br.ufma.atividade3.service.exceptions.ErroEgressoRunTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers =  EgressoController.class)
@AutoConfigureMockMvc
public class EgressoControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    EgressoService service;

    @Test
    public void deveSalvarEgresso () throws Exception {
        final String API = "http://localhost:8080/api/egresso/salvar";
        //cenário
        //dto para virar json
        EgressoDTO dto = EgressoDTO.builder().nome("Eduarda")
                .email("eduarda@email.com")
                .cpf("12345678910")
                .resumo("resumido")
                .url_foto("fota")
                .build();

        //resposta que será mock
        Egresso egresso = Egresso.builder().nome("Eduarda")
                .email("eduarda@email.com")
                .cpf("12345678910")
                .resumo("resumido")
                .url_foto("fota")
                .build();

        //mock salvar
        Mockito.when(service.salvarEgresso(Mockito.any(Egresso.class))).thenReturn(egresso);

        //converte DTO para json
        String json = new ObjectMapper().writeValueAsString(dto);

        //ação
        //constrói a requisição post
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post(API)   //na URI
                        .accept ( MediaType.APPLICATION_JSON)
                        .contentType (MediaType.APPLICATION_JSON)
                        .content (json); //mandando o DTO

        //ação e verificação
        mvc.perform(request)
                .andExpect(   //teste em si
                        MockMvcResultMatchers.status().isCreated()); //espera 200
    }

    @Test
    public void deveDeletarEgresso () throws Exception {

    }

}
