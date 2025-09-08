package br.com.localizador.repository;

import br.com.localizador.model.Agencia;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AgenciaRepositorioTest {
    @Autowired AgenciaRepositorio repositorio;

    @Test void devePersistirEListar() {
        var salvo = repositorio.save(new Agencia(10, -5));
        assertThat(salvo.getId()).isNotNull();
        assertThat(repositorio.findAll()).isNotEmpty();
    }
}
