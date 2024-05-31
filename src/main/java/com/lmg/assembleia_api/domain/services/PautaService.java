package com.lmg.assembleia_api.domain.services;

import com.lmg.assembleia_api.infrastructure.model.Pauta;
import com.lmg.assembleia_api.infrastructure.repository.PautaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Transactional
    public Pauta salvar(Pauta pauta) {
        log.info("Salvando uma Pauta...");
        return pautaRepository.save(pauta);
    }
}
