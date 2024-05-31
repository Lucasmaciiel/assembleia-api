package com.lmg.assembleia_api.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class VotoResponse {

    private Integer id;
    private String cpfCooperado;
    private Boolean opcaoEscolhida;
    private PautaResponse pauta;

}
