package br.com.localizador.controller;

import br.com.localizador.dto.RequisicaoAgencia;
import br.com.localizador.dto.RespostaAgencia;
import br.com.localizador.service.AgenciaServico;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AgenciaControlador.class)
class AgenciaControladorTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    AgenciaServico servico;

    @Test
    void deveCadastrarAgencia() throws Exception {
        Mockito.when(servico.cadastrar(any(RequisicaoAgencia.class)))
                .thenReturn(new RespostaAgencia(1L, 10, -5, "AGENCIA_1"));

        mvc.perform(post("/agencias/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"posX\":10,\"posY\":-5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("AGENCIA_1"))
                .andExpect(jsonPath("$.posX").value(10))
                .andExpect(jsonPath("$.posY").value(-5));
    }

    @Test
    void deveRetornarDistanciasOrdenadas() throws Exception {
        Map<String,String> resposta = new LinkedHashMap<>();
        resposta.put("AGENCIA_2", "distancia = 2.20");
        resposta.put("AGENCIA_1", "distancia = 10.00");

        Mockito.when(servico.calcularDistancias(-10, 5)).thenReturn(resposta);

        mvc.perform(get("/desafio/distancia").param("posX","-10").param("posY","5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.AGENCIA_2").value("distancia = 2.20"))
                .andExpect(jsonPath("$.AGENCIA_1").value("distancia = 10.00"));
    }
}
