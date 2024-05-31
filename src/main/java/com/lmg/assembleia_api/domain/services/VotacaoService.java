package com.lmg.assembleia_api.domain.services;

import com.lmg.assembleia_api.common.exceptions.EntidadeNaoEncontradaException;
import com.lmg.assembleia_api.domain.dto.response.PautaResponse;
import com.lmg.assembleia_api.domain.dto.response.VotacaoResponse;
import com.lmg.assembleia_api.infrastructure.model.Voto;
import com.lmg.assembleia_api.infrastructure.repository.SessaoRepository;
import com.lmg.assembleia_api.infrastructure.repository.VotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotacaoService {

    public static final String VOTACAO_NAO_ENCONTRADA = "Votação não encontrada";

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
    public VotacaoResponse buscarResultado(Integer pautaId) {
        List<Voto> votos = votoRepository.findByPautaId(pautaId);

        if (votos.isEmpty()) {
            throw new EntidadeNaoEncontradaException(VOTACAO_NAO_ENCONTRADA);
        }

        var pauta = votos.iterator().next().getPauta();

        Long totalSessoes = sessaoRepository.countByPautaId(pauta.getId());

        Integer total = votos.size();

        Integer totalSim = (int) votos.stream()
                .filter(voto -> Boolean.TRUE.equals(voto.getEscolha()))
                .count();

        Integer totalNao = total - totalSim;

        return VotacaoResponse.builder()
                .pauta(PautaResponse.builder().build())
                .totalVotos(total)
                .sessoesTotal(totalSessoes.intValue())
                .totalSim(totalSim)
                .totalNao(totalNao)
                .build();
    }
}
