package br.com.localizador.dto;

import jakarta.validation.constraints.NotNull;

public record RequisicaoAgencia(@NotNull Integer posX, @NotNull Integer posY) {

}

