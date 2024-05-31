package com.lmg.assembleia_api.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class SessaoResponse {

    private Integer id;
    private LocalDateTime dataInicio;
    private LocalDateTime expiraEm;
    private PautaResponse pauta;
}
