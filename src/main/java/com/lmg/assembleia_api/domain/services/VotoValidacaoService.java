package com.lmg.assembleia_api.domain.services;

import com.lmg.assembleia_api.common.exceptions.EstadoInvalidoException;
import com.lmg.assembleia_api.infrastructure.model.Sessao;
import com.lmg.assembleia_api.infrastructure.model.Voto;
import com.lmg.assembleia_api.infrastructure.repository.VotoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VotoValidacaoService {

    public static final String SESSAO_EXPIRADA = "Tempo de sessão expirada";
    public static final String VOTO_JA_EXISTE = "Voto ja existente para o CPF: %s";


    private final VotoRepository votoRepository;

    public VotoValidacaoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public void verificarSessao(final Sessao sessao) {
        LocalDateTime deadline = sessao.getDataInicio().plusMinutes(sessao.getMinutosExpiracao());

        if (LocalDateTime.now().isAfter(deadline)) {
            throw new EstadoInvalidoException(SESSAO_EXPIRADA);
        }
    }

    public void verificarVotoExistente(final String cpf, Integer pautaId) {
        votoRepository.findByCpfAndPautaId(cpf, pautaId)
                .ifPresent(v -> {
                    throw new EstadoInvalidoException(String.format(VOTO_JA_EXISTE, v.getCpf()));
                });
    }

    public void verificarCooperado(final Voto voto) {
        //todo: implementar
    }

}
