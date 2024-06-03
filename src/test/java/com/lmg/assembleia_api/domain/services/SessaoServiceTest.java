package com.lmg.assembleia_api.domain.services;

import com.lmg.assembleia_api.common.exceptions.EstadoInvalidoException;
import com.lmg.assembleia_api.domain.dto.response.SessaoResponse;
import com.lmg.assembleia_api.domain.mappers.SessaoMapper;
import com.lmg.assembleia_api.infrastructure.model.Pauta;
import com.lmg.assembleia_api.infrastructure.model.Sessao;
import com.lmg.assembleia_api.infrastructure.repository.PautaRepository;
import com.lmg.assembleia_api.infrastructure.repository.SessaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessaoServiceTest {

    @InjectMocks
    private SessaoService sessaoService;

    @Mock
    private PautaRepository pautaRepository;
    @Mock
    private SessaoRepository sessaoRepository;
    @Mock
    private SessaoMapper sessaoMapper;

    @Test
    @DisplayName("Cria uma sessão com data de inicio e expiração preenchidos na request")
    void deveriaCriarSessao() {
        var pautaId = 45;
        Pauta pauta = Pauta.builder().id(45).build();

        var sessao = Sessao.builder()
                .pauta(pauta)
                .dataInicio(LocalDateTime.parse("2024-06-02T13:28:21.702"))
                .minutosExpiracao(10)
                .build();

        var sessaoDBMock = Sessao.builder()
                .id(56)
                .pauta(pauta)
                .dataInicio(LocalDateTime.parse("2024-06-02T13:28:21.702"))
                .minutosExpiracao(10)
                .build();

        when(pautaRepository.findById(pautaId)).thenReturn(Optional.of(pauta));
        when(sessaoRepository.save(any(Sessao.class))).thenReturn(sessaoDBMock);
        when(sessaoMapper.toResponse(any(Sessao.class))).thenReturn(getSessaoResponse());

        assertThat(sessaoService.criarSessao(pautaId, sessao)).isEqualTo(getSessaoResponse());
        verify(sessaoRepository).save(any(Sessao.class));
        verify(sessaoMapper).toResponse(any(Sessao.class));
    }

    @Test
    @DisplayName("Cria uma sessão com expiração em minutos com valor default")
    void deveriaCriarSessaoComExpiracaoDefault() {
        var pautaId = 45;
        Pauta pauta = Pauta.builder().id(45).build();

        var sessao = Sessao.builder()
                .pauta(pauta)
                .dataInicio(null)
                .minutosExpiracao(null)
                .build();

        var sessaoDBMock = Sessao.builder()
                .id(56)
                .pauta(pauta)
                .dataInicio(LocalDateTime.parse("2024-06-02T13:28:21.702"))
                .minutosExpiracao(1)
                .build();

        ArgumentCaptor<Sessao> sessaoCaptor = ArgumentCaptor.forClass(Sessao.class);

        when(pautaRepository.findById(pautaId)).thenReturn(Optional.of(pauta));
        when(sessaoRepository.save(any(Sessao.class))).thenReturn(sessaoDBMock);
        when(sessaoMapper.toResponse(any(Sessao.class))).thenReturn(getSessaoResponse());

        sessaoService.criarSessao(pautaId, sessao);

        int expiracaoMinutosEsperado = 1;

        verify(sessaoRepository).save(sessaoCaptor.capture());
        assertThat(sessaoCaptor.getValue().getMinutosExpiracao()).isEqualTo(expiracaoMinutosEsperado);
    }

    private SessaoResponse getSessaoResponse() {
        return SessaoResponse.builder()
                .dataInicio(LocalDateTime.parse("2024-06-02T13:28:21.702"))
                .expiraEm(LocalDateTime.parse("2024-06-02T13:38:21.702"))
                .build();
    }

    @Test
    @DisplayName("Deveria buscar corretamente")
    void deveriaBuscarPorIdEPautaId() {
        var sessaoMock = getSessao();
        var esperado = getSessao();

        when(sessaoRepository.findByIdAndPautaId(2, 5)).thenReturn(Optional.ofNullable(sessaoMock));

        var sessao = assertDoesNotThrow(() -> sessaoService.buscarPorIdEPautaId(2, 5));
        assertThat(sessao).usingRecursiveComparison().isEqualTo(esperado);

    }

    @Test
    @DisplayName("Deveria lançar exception EstadoInvalidoException")
    void deveriaLancarExceptionCasoSessaoNaoEncontrada() {
        final int sessaoId = 2;
        final int pautaId = 5;
        when(sessaoRepository.findByIdAndPautaId(sessaoId, pautaId)).thenThrow(new EstadoInvalidoException("Sessão com o ID: 2, não existe na Pauta de ID: 5"));

        var exception = assertThrows(EstadoInvalidoException.class, () -> sessaoService.buscarPorIdEPautaId(sessaoId, pautaId));
        assertThat(exception.getMessage()).isEqualTo("Sessão com o ID: 2, não existe na Pauta de ID: 5");

    }

    private Sessao getSessao() {
        return Sessao.builder()
                .pauta(Pauta.builder().id(45).build())
                .dataInicio(LocalDateTime.parse("2024-06-02T13:28:21.702"))
                .minutosExpiracao(10)
                .build();
    }

}