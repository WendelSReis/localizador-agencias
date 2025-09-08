package br.com.localizador.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraDistanciaTest {

    @Test
    void deveCalcularDistanciaEuclidiana(){
        assertEquals(5.0,
                CalculadoraDistancia.euclidiana(0,0,3,4), 1e-9);
    }
    @Test
    void deveArredondarDuasCasas(){
        assertEquals(2.35,
                CalculadoraDistancia.arredondar2(2.345), 1e-9);
    }
}