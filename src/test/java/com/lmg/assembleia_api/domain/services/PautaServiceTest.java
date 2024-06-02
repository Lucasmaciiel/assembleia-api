package com.lmg.assembleia_api.domain.services;

import com.lmg.assembleia_api.infrastructure.model.Pauta;
import com.lmg.assembleia_api.infrastructure.repository.PautaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @InjectMocks
    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;
    @Test
    @DisplayName("Deveria salvar uma pauta corretamente")
    void deveriaSalvarUmaPauta() {
        var pauta = Pauta.builder()
                .nome("Nova Pauta")
                .build();

        var pautaMock = getPauta();
        var pautaExpected = getPauta();

        ArgumentCaptor<Pauta> pautaCaptor = ArgumentCaptor.forClass(Pauta.class);

        when(pautaRepository.save(pauta)).thenReturn(pautaMock);

        assertThat(pautaService.salvar(pauta)).usingRecursiveComparison().isEqualTo(pautaExpected);
        verify(pautaRepository).save(pautaCaptor.capture());
        assertEquals(pauta, pautaCaptor.getValue());
    }

    private static Pauta getPauta() {
        return Pauta.builder().id(1).nome("Nova Pauta").build();
    }
}