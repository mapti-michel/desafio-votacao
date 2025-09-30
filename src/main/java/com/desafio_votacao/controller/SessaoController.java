package com.desafio_votacao.controller;

import com.desafio_votacao.dto.SessaoRequest;
import com.desafio_votacao.entity.Pauta;
import com.desafio_votacao.entity.Sessao;
import com.desafio_votacao.service.PautaService;
import com.desafio_votacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessoes")
@RequiredArgsConstructor
public class SessaoController {

    private final PautaService pautaService;
    private final SessaoService sessaoService;

    @PostMapping
    public ResponseEntity<Sessao> abrirSessao(@RequestBody SessaoRequest request) {
        Pauta pauta = pautaService.buscarPorId(request.getPautaId());
        Sessao sessao = sessaoService.abrirSessao(pauta, request.getDuracaoEmMinutos());
        return ResponseEntity.ok(sessao);
    }

    @GetMapping
    public ResponseEntity<List<Sessao>> listarSessoes() {
        return ResponseEntity.ok(sessaoService.listarSessoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sessao> buscarSessao(@PathVariable Long id) {
        return ResponseEntity.ok(sessaoService.buscarPorId(id));
    }
}
