package br.com.localizador.service;

import br.com.localizador.dto.RequisicaoAgencia;
import br.com.localizador.dto.RespostaAgencia;
import br.com.localizador.model.Agencia;
import br.com.localizador.repository.AgenciaRepositorio;
import br.com.localizador.util.CalculadoraDistancia;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AgenciaServico {

    private final AgenciaRepositorio repositorio;

    public AgenciaServico(AgenciaRepositorio repositorio) { this.repositorio = repositorio; }

    public RespostaAgencia cadastrar(RequisicaoAgencia req) {
        var salvo = repositorio.save(new Agencia(req.posX(), req.posY()));
        return new RespostaAgencia(salvo.getId(), salvo.getPosX(), salvo.getPosY(), nome(salvo.getId()));
    }

    public Map<String,String> calcularDistancias(int usuarioX, int usuarioY) {
        return repositorio.findAll().stream()
                .map(a -> new AbstractMap.SimpleEntry<>(a,
                        CalculadoraDistancia.arredondar2(
                                CalculadoraDistancia.euclidiana(usuarioX, usuarioY, a.getPosX(), a.getPosY()))))
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .collect(Collectors.toMap(
                        e -> nome(e.getKey().getId()),
                        e -> "distancia = " + String.format(java.util.Locale.US,"%.2f", e.getValue()),
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    private String nome(Long id){ return "AGENCIA_" + id; }
}
