package com.lmg.assembleia_api.domain.mapper;

import com.lmg.assembleia_api.domain.dto.request.PautaRequest;
import com.lmg.assembleia_api.domain.dto.response.PautaResponse;
import com.lmg.assembleia_api.infrastructure.model.Pauta;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class PautaMapper {

    private final ModelMapper modelMapper;

    public PautaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Pauta toModel(PautaRequest pautaRequest){
        return modelMapper.map(pautaRequest, Pauta.class);
    }

    public PautaResponse toResponse(Pauta pauta) {
        return modelMapper.map(pauta, PautaResponse.class);
    }

}
