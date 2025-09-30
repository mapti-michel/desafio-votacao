package com.desafio_votacao.desafio_votacao.service;

import static org.junit.jupiter.api.Assertions.*;

import com.desafio_votacao.service.PautaService;
import com.desafio_votacao.service.SessaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.desafio_votacao.entity.Sessao;
import com.desafio_votacao.entity.Pauta;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SessaoServiceTest {

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private PautaService pautaService;

    @Test
    void deveAbrirSessaoComTempoPadrao() {
        Pauta pauta = new Pauta();
        pauta.setTitulo("Pauta Teste");
        pauta.setDescricao("Descrição");
        pauta = pautaService.criarPauta(pauta);

        Sessao sessao = sessaoService.abrirSessao(pauta, null); // null = default 1 min

        assertNotNull(sessao);
        assertEquals(pauta.getId(), sessao.getPauta().getId());
        assertTrue(sessao.getFim().isAfter(sessao.getInicio()));
    }
}

