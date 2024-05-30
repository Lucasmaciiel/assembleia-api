package com.lmg.assembleia_api.common.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
@Setter
public class MensagemErro {

    private Instant timeStamp;
    private Integer status;
    private String erro;
    private String mensagem;

}
