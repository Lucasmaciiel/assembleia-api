package com.lmg.assembleia_api.domain.controllers;

import com.lmg.assembleia_api.domain.dto.request.VotoRequest;
import com.lmg.assembleia_api.domain.dto.response.VotoResponse;
import com.lmg.assembleia_api.domain.mappers.VotoMapper;
import com.lmg.assembleia_api.domain.services.VotoFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("votos")
@Tag(name = "Voto", description = "Realiza um voto")
public class VotoController {

    private final VotoFacade votoFacade;
    private final VotoMapper votoMapper;


    public VotoController(VotoFacade votoFacade, VotoMapper votoMapper) {
        this.votoFacade = votoFacade;
        this.votoMapper = votoMapper;
    }

    @Operation(summary = "Salva um voto")
    @PostMapping("pautas/{pautaId}/sessoes/{sessaoId}")
    public VotoResponse save(@PathVariable Integer pautaId,
                             @PathVariable Integer sessaoId,
                             @Valid @RequestBody VotoRequest votoRequest) {
        var voto = votoMapper.toModel(votoRequest);
        return votoMapper.toResponse(votoFacade.salvar(pautaId, sessaoId, voto));
    }

}
