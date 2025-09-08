package br.com.localizador.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class CalculadoraDistancia {
    private CalculadoraDistancia(){}

    public static double euclidiana(int x1, int y1, int x2, int y2) {
        return Math.hypot(x1 - x2, y1 - y2);
    }

    public static double arredondar2(double valor) {
        return BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}

