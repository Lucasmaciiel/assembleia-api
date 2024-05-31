package com.lmg.assembleia_api.domain.controllers;

import com.lmg.assembleia_api.domain.dto.request.PautaRequest;
import com.lmg.assembleia_api.domain.dto.response.PautaResponse;
import com.lmg.assembleia_api.domain.mappers.PautaMapper;
import com.lmg.assembleia_api.domain.services.PautaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pautas")
public class PautaController {

    private final PautaService pautaService;
    private final PautaMapper pautaMapper;

    public PautaController(PautaService pautaService, PautaMapper pautaMapper) {
        this.pautaService = pautaService;
        this.pautaMapper = pautaMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PautaResponse salvar(@Valid @RequestBody PautaRequest pautaRequest) {
        var pauta = pautaMapper.toModel(pautaRequest);
        return pautaMapper.toResponse(pautaService.salvar(pauta));
    }
}
