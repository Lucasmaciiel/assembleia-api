package com.lmg.assembleia_api.common.exceptions;

import java.io.Serial;

public class EntidadeNaoEncontradaException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -2101508677227447057L;

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

}
