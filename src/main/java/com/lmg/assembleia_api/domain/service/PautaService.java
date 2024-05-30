package com.lmg.assembleia_api.domain.service;

import com.lmg.assembleia_api.infrastructure.model.Pauta;
import com.lmg.assembleia_api.infrastructure.repository.PautaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Transactional
    public Pauta salvar(Pauta pauta) {
        return pautaRepository.save(pauta);
    }
}
