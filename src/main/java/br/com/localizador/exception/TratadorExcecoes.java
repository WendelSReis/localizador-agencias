package br.com.localizador.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@ControllerAdvice
public class TratadorExcecoes {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> validar(MethodArgumentNotValidException ex) {
        Map<String,Object> body = new HashMap<>();
        body.put("mensagem", "Erro de validação");
        body.put("erros", ex.getBindingResult().getFieldErrors().stream()
                .map(f -> Map.of("campo", f.getField(), "mensagem", f.getDefaultMessage()))
                .toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> generico(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("mensagem","Erro interno"));
    }
}
