package com.lmg.assembleia_api.domain.mappers;

import com.lmg.assembleia_api.domain.dto.request.SessaoRequest;
import com.lmg.assembleia_api.domain.dto.response.SessaoResponse;
import com.lmg.assembleia_api.infrastructure.model.Sessao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SessaoMapper {

    public SessaoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private final ModelMapper modelMapper;

    public SessaoResponse toResponse(Sessao obj) {
        return modelMapper.map(obj, SessaoResponse.class);
    }

    public Sessao toModel(SessaoRequest request) {
        return modelMapper.map(request, Sessao.class);
    }

}
