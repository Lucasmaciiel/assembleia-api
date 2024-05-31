package com.lmg.assembleia_api.common.exceptions;

import java.io.Serial;

public class EstadoInvalidoException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -6114443570603064297L;

    public EstadoInvalidoException(String message) {
        super(message);
    }
}
