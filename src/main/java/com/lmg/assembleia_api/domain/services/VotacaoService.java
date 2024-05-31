package com.lmg.assembleia_api.domain.services;

import com.lmg.assembleia_api.common.exceptions.EntidadeNaoEncontradaException;
import com.lmg.assembleia_api.domain.dto.VotacaoTotalDTO;
import com.lmg.assembleia_api.domain.dto.response.VotacaoResponse;
import com.lmg.assembleia_api.infrastructure.model.Voto;
import com.lmg.assembleia_api.infrastructure.repository.SessaoRepository;
import com.lmg.assembleia_api.infrastructure.repository.VotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class VotacaoService {

    public static final String VOTACAO_NAO_ENCONTRADA = "Votação não encontrada com PautaId: %s";

    private final VotoRepository votoRepository;
    private final SessaoRepository sessaoRepository;

    public VotacaoService(VotoRepository votoRepository, SessaoRepository sessaoRepository) {
        this.votoRepository = votoRepository;
        this.sessaoRepository = sessaoRepository;
    }

    /**
     * Método que constrói o resultado da votação
     *
     * @param pautaId Id da Pauta
     * @return VotacaoResponse
     */
    @Transactional(readOnly = true)
    public VotacaoResponse buscarResultado(Integer pautaId) {
        log.info("Buscando resultado da votação da Pauta {}", pautaId);

        List<Voto> votos = votoRepository.findByPautaId(pautaId);

        if (CollectionUtils.isEmpty(votos)) {
            log.error("Votação não encontrada com a pautaID {}", pautaId);
            throw new EntidadeNaoEncontradaException(String.format(VOTACAO_NAO_ENCONTRADA, pautaId));
        }

        Integer totalVotos = votos.size();
        Integer totalSim = calcularSim(votos);
        Integer totalNao = calcularNao(totalVotos, totalSim);

        return VotacaoResponse.builder()
                .pautaId(pautaId)
                .totalVotacao(VotacaoTotalDTO.builder()
                        .totalVotos(totalVotos)
                        .sessoesTotal(sessaoRepository.countByPautaId(pautaId).intValue())
                        .totalSim(totalSim)
                        .totalNao(totalNao)
                        .build())
                .build();
    }

    private Integer calcularSim(List<Voto> votos) {
        return (int) votos.stream()
                .filter(voto -> Boolean.TRUE.equals(voto.getEscolha()))
                .count();
    }

    private Integer calcularNao(Integer total, Integer totalSim) {
        return total - totalSim;
    }
}
