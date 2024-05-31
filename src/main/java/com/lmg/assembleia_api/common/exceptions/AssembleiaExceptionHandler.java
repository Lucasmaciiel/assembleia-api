package com.lmg.assembleia_api.common.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class AssembleiaExceptionHandler {

    private static final String MENSAGEM_ERRO = "Não foi possível processar a solicitação, verifique os parâmetros";

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MensagemErro> handleBadRequest(BadRequestException e) {
        return getBadRequestResponse(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemErro> handleBadRequest(MethodArgumentNotValidException e) {
        var erros = e.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        String mensagemErro = String.join(", ", erros);
        return getBadRequestResponse(mensagemErro);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<MensagemErro> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException e) {
        return getBadRequestResponse(e.getMessage());
    }

    private static ResponseEntity<MensagemErro> getBadRequestResponse(String mensagem) {
        return status(HttpStatus.BAD_REQUEST).body(MensagemErro.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .erro(MENSAGEM_ERRO)
                .mensagem(mensagem)
                .build());
    }
}
