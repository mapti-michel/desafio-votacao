package com.desafio_votacao.desafio_votacao.service;

import com.desafio_votacao.entity.Pauta;
import com.desafio_votacao.service.PautaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PautaServiceTest {

    @Autowired
    private PautaService pautaService;

    @Test
    void deveSalvarPautaComSucesso() {
        Pauta pauta = new Pauta();
        pauta.setTitulo("Teste Pauta");
        pauta.setDescricao("Descrição teste");

        Pauta salva = pautaService.criarPauta(pauta);
        assertNotNull(salva.getId());
        assertEquals("Teste Pauta", salva.getTitulo());
    }

    @Test
    void deveBuscarPautaPorId() {
        Pauta pauta = new Pauta();
        pauta.setTitulo("Buscar");
        pauta.setDescricao("Descrição");

        Pauta salva = pautaService.criarPauta(pauta);
        Pauta encontrada = pautaService.buscarPorId(salva.getId());

        assertNotNull(encontrada);
        assertEquals(salva.getId(), encontrada.getId());
    }
}
