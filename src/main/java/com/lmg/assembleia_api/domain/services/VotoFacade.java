package com.lmg.assembleia_api.domain.services;

import com.lmg.assembleia_api.infrastructure.model.Sessao;
import com.lmg.assembleia_api.infrastructure.model.Voto;
import com.lmg.assembleia_api.infrastructure.repository.VotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class VotoFacade {

    private final SessaoService sessaoService;
    private final VotoRepository votoRepository;
    private final VotoValidacaoService votoValidacaoService;

    public VotoFacade(SessaoService sessaoService, VotoRepository votoRepository, VotoValidacaoService votoValidacaoService) {
        this.sessaoService = sessaoService;
        this.votoRepository = votoRepository;
        this.votoValidacaoService = votoValidacaoService;
    }

    @Transactional
    public Voto salvar(Integer pautaId, Integer sessaoId, Voto voto) {
        log.info("Salvando um novo voto com a PautaId: {}, SessãoId {}", pautaId, sessaoId);
        Sessao sessao = sessaoService.findByIdAndPautaId(sessaoId, pautaId);
        voto.setPauta(sessao.getPauta());

        votoValidacaoService.verificarSessao(sessao);
        votoValidacaoService.verificarVotoExistente(voto.getCpf(), voto.getPauta().getId());
        votoValidacaoService.verificarCooperado(voto);

        return votoRepository.save(voto);
    }

}
