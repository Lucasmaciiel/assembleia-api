package com.lmg.assembleia_api.domain.mappers;

import com.lmg.assembleia_api.domain.dto.request.VotoRequest;
import com.lmg.assembleia_api.domain.dto.response.VotoResponse;
import com.lmg.assembleia_api.infrastructure.model.Voto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VotoMapper {

    private final ModelMapper modelMapper;

    public VotoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public VotoResponse toResponse(Voto voto) {
        return modelMapper.map(voto, VotoResponse.class);
    }
    public Voto toModel(VotoRequest voto) {
        return modelMapper.map(voto, Voto.class);
    }
}
