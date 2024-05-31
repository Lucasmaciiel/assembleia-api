package com.lmg.assembleia_api.domain.controllers;

import com.lmg.assembleia_api.domain.dto.response.VotacaoResponse;
import com.lmg.assembleia_api.domain.services.VotacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("votacoes")
@Tag(name = "Votação", description = "Resultado da votação")
public class VotacaoController {

    private final VotacaoService votacaoService;

    public VotacaoController(VotacaoService votacaoService) {
        this.votacaoService = votacaoService;
    }

    @Operation(summary = "Retorna o resultado da votação")
    @GetMapping("pautas/{pautaId}")
    @ResponseStatus(code = HttpStatus.OK)
    public VotacaoResponse buscarVotosByPautaId(@PathVariable Integer pautaId) {
        return votacaoService.buscarResultado(pautaId);
    }
}
