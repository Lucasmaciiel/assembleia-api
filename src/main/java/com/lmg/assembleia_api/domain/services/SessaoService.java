package com.lmg.assembleia_api.domain.services;

import com.lmg.assembleia_api.common.exceptions.EntidadeNaoEncontradaException;
import com.lmg.assembleia_api.common.exceptions.EstadoInvalidoException;
import com.lmg.assembleia_api.domain.dto.response.SessaoResponse;
import com.lmg.assembleia_api.domain.mappers.SessaoMapper;
import com.lmg.assembleia_api.infrastructure.model.Sessao;
import com.lmg.assembleia_api.infrastructure.repository.PautaRepository;
import com.lmg.assembleia_api.infrastructure.repository.SessaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
public class SessaoService {

    public static final String PAUTA_NAO_ENCONTRADA = "Pauta não encontrada com o ID: ";
    private static final int MINUTOS_EXPIRACAO_DEFAULT = 1;

    private final PautaRepository pautaRepository;
    private final SessaoRepository sessaoRepository;
    private final SessaoMapper sessaoMapper;

    public SessaoService(PautaRepository pautaRepository, SessaoRepository sessaoRepository, SessaoMapper sessaoMapper) {
        this.pautaRepository = pautaRepository;
        this.sessaoRepository = sessaoRepository;
        this.sessaoMapper = sessaoMapper;
    }

    @Transactional
    public SessaoResponse criarSessao(Integer pautaId, Sessao sessao) {
        log.info("Abrindo uma sessão...");
        var pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(PAUTA_NAO_ENCONTRADA + pautaId));

        sessao.setPauta(pauta);

        if (Objects.isNull(sessao.getDataInicio())) {
            sessao.setDataInicio(LocalDateTime.now());
        }

        Integer minutosExpiracao = sessao.getMinutosExpiracao();

        if (Objects.isNull(sessao.getMinutosExpiracao())) {
            sessao.setMinutosExpiracao(MINUTOS_EXPIRACAO_DEFAULT);
            minutosExpiracao = MINUTOS_EXPIRACAO_DEFAULT;
        }

        var sessaoSalva = sessaoRepository.save(sessao);
        log.info("Sessão Id: {} salva com sucesso", sessaoSalva.getId());

        LocalDateTime expiraEm = sessaoSalva.getDataInicio().plusMinutes(minutosExpiracao);

        SessaoResponse sessaoResponse = sessaoMapper.toResponse(sessaoSalva);
        sessaoResponse.setExpiraEm(expiraEm);
        return sessaoResponse;
    }

    public Sessao buscarPorIdEPautaId(Integer sessaoId, Integer pautaId) {
        return sessaoRepository.findByIdAndPautaId(sessaoId, pautaId).orElseThrow(
                () -> new EstadoInvalidoException(String.format("Sessão com o ID: %d, não existe na Pauta de ID: %d", pautaId, sessaoId)));
    }
}
