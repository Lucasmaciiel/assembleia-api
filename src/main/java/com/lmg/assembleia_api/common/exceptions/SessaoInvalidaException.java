package com.lmg.assembleia_api.common.exceptions;

import java.io.Serial;

public class SessaoInvalidaException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1859041365497927333L;

    public SessaoInvalidaException(String message) {
        super(message);
    }
}
