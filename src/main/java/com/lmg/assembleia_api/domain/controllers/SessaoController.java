package com.lmg.assembleia_api.domain.controllers;

import com.lmg.assembleia_api.domain.dto.request.SessaoRequest;
import com.lmg.assembleia_api.domain.dto.response.SessaoResponse;
import com.lmg.assembleia_api.domain.mappers.SessaoMapper;
import com.lmg.assembleia_api.domain.services.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sessoes")
@Tag(name = "Sessão", description = "Abre uma nova sessão")
public class SessaoController {

    private final SessaoService sessaoService;
    private final SessaoMapper sessaoMapper;

    public SessaoController(SessaoService sessaoService, SessaoMapper sessaoMapper) {
        this.sessaoService = sessaoService;
        this.sessaoMapper = sessaoMapper;
    }

    @PostMapping("pautas/{pautaId}")
    @Operation(summary = "Abre uma nova sessão")
    @ResponseStatus(HttpStatus.CREATED)
    public SessaoResponse save(@PathVariable Integer pautaId, @Valid @RequestBody SessaoRequest sessaoRequest) {
        return sessaoService.criarSessao(pautaId, sessaoMapper.toModel(sessaoRequest));
    }
}
