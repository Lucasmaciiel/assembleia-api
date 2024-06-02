package com.lmg.assembleia_api.domain.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SessaoRequest {

    private LocalDateTime dataInicio;

    @Valid
    @Positive(message = "A sessão deve ser de no mínimo 1 minuto")
    private Integer minutosExpiracao;

}
