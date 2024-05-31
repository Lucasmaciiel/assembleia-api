package com.lmg.assembleia_api.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotacaoTotalDTO {

    private Integer totalSim;
    private Integer totalNao;
    private Integer totalVotos;
    private Integer sessoesTotal;
}
