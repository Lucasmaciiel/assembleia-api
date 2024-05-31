package com.lmg.assembleia_api.domain.dto.response;

import com.lmg.assembleia_api.domain.dto.VotacaoTotalDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotacaoResponse {

    private Integer pautaId;
    private VotacaoTotalDTO totalVotacao;

}
