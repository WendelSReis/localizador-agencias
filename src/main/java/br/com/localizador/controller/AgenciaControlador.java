package br.com.localizador.controller;

import br.com.localizador.dto.RequisicaoAgencia;
import br.com.localizador.dto.RespostaAgencia;
import br.com.localizador.service.AgenciaServico;
import jakarta.validation.Valid;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class AgenciaControlador {

    private static final Logger LOG = LoggerFactory.getLogger(AgenciaControlador.class);
    private final AgenciaServico servico;

    public AgenciaControlador(AgenciaServico servico) { this.servico = servico; }

    @PostMapping({"/agencias/cadastrar", "/desafio/cadastrar"})
    public ResponseEntity<RespostaAgencia> cadastrar(@Valid @RequestBody RequisicaoAgencia req) {
        var resposta = servico.cadastrar(req);
        LOG.info("Agência cadastrada: {} ({}, {})", resposta.nome(), resposta.posX(), resposta.posY());
        return ResponseEntity.ok(resposta);
    }

    @GetMapping({"/agencias/distancia", "/desafio/distancia"})
    public ResponseEntity<Map<String,String>> distancia(@RequestParam int posX, @RequestParam int posY) {
        var mapa = servico.calcularDistancias(posX, posY);
        LOG.info("Distâncias para usuário ({}, {}) -> {} agências", posX, posY, mapa.size());
        return ResponseEntity.ok(mapa);
    }
}

