package com.desafio_votacao.desafio_votacao.service;

import com.desafio_votacao.entity.OpcaoVoto;
import com.desafio_votacao.entity.Pauta;
import com.desafio_votacao.entity.Sessao;
import com.desafio_votacao.entity.Voto;
import com.desafio_votacao.repository.SessaoRepository;
import com.desafio_votacao.repository.VotoRepository;
import com.desafio_votacao.service.CpfService;
import com.desafio_votacao.service.PautaService;
import com.desafio_votacao.service.SessaoService;
import com.desafio_votacao.service.VotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VotoServiceTest {

    @Autowired
    private VotoService votoService;

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private PautaService pautaService;

    @MockBean
    private CpfService cpfService; // Mock do CPF para não dar aleatório

    private Sessao sessao;

    @BeforeEach
    void setup() {
        // Forçar CPF válido
        Mockito.when(cpfService.verificarCpf(Mockito.anyString())).thenReturn("ABLE_TO_VOTE");
        Mockito.when(cpfService.cpfValido(Mockito.anyString())).thenReturn(true);

        // Criar Pauta e Sessão válidas
        Pauta pauta = new Pauta();
        pauta.setTitulo("Título teste");
        pauta.setDescricao("Descrição teste");
        pauta = pautaService.criarPauta(pauta);

        sessao = sessaoService.abrirSessao(pauta, 1L); // 1 minuto de duração
    }

    @Test
    void deveRegistrarVotoComSucesso() {
        Voto voto = votoService.registrarVoto(sessao.getId(), "12345678900", OpcaoVoto.SIM);
        assertNotNull(voto.getId());
        assertEquals(OpcaoVoto.SIM, voto.getOpcao());
        assertEquals("12345678900", voto.getAssociadoId());
    }

    @Test
    void deveRetornarResultadoCorreto() {
        votoService.registrarVoto(sessao.getId(), "12345678900", OpcaoVoto.SIM);
        votoService.registrarVoto(sessao.getId(), "98765432100", OpcaoVoto.NAO);

        Map<String, Long> resultado = votoService.contabilizarVotos(sessao.getId());

        assertEquals(1L, resultado.get("SIM"));
        assertEquals(1L, resultado.get("NAO"));
    }

    @Test
    void naoPodeVotarComCpfInvalido() {
        Mockito.when(cpfService.cpfValido("11111111111")).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                votoService.registrarVoto(sessao.getId(), "11111111111", OpcaoVoto.SIM));

        assertEquals("CPF inválido", exception.getReason());
    }
}
