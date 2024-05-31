package com.lmg.assembleia_api.domain.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotacaoResponse {

    private PautaResponse pauta;
    private Integer totalSim;
    private Integer totalNao;
    private Integer totalVotos;
    private Integer sessoesTotal;
}
