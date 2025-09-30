package com.desafio_votacao.desafio_votacao.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.desafio_votacao.entity.Pauta;
import com.desafio_votacao.repository.PautaRepository;
import com.desafio_votacao.service.PautaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class PautaServiceTest {

    @InjectMocks
    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarPautaComSucesso() {
        Pauta pauta = new Pauta();
        pauta.setTitulo("Teste");
        pauta.setDescricao("Descrição Teste");

        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);

        Pauta resultado = pautaService.salvarPauta(pauta);

        assertNotNull(resultado);
        assertEquals("Teste", resultado.getTitulo());
    }

    @Test
    void deveBuscarPautaPorId() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setTitulo("Teste");

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));

        Pauta resultado = pautaService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }
}

