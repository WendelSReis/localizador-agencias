package br.com.localizador.service;

import br.com.localizador.dto.RequisicaoAgencia;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AgenciaServicoIntegracaoTest {
    @Autowired AgenciaServico servico;

    @Test void deveCalcularDistanciasOrdenadas() {
        servico.cadastrar(new RequisicaoAgencia( 0, 1));
        servico.cadastrar(new RequisicaoAgencia(10,10));
        var mapa = servico.calcularDistancias(0,0);
        assertThat(mapa).isNotEmpty();
        assertThat(mapa.values().iterator().next()).startsWith("distancia = ");
    }
}
