package com.lmg.assembleia_api.common.exceptions;

import java.io.Serial;

public class ArgumentoInvalidoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3711589229883454389L;

    public ArgumentoInvalidoException(String message) {
        super(message);
    }
}
