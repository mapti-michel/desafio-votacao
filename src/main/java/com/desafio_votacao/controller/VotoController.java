package com.desafio_votacao.controller;

import com.desafio_votacao.entity.OpcaoVoto;
import com.desafio_votacao.entity.Voto;
import com.desafio_votacao.service.VotoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/votos")
@RequiredArgsConstructor
public class VotoController {

    private final VotoService votoService;

    @PostMapping
    public ResponseEntity<Voto> registrarVoto(@RequestBody VotoRequest request) {
        Voto voto = votoService.registrarVoto(
                request.getSessaoId(),
                request.getAssociadoId(),
                request.getOpcao()
        );
        return ResponseEntity.ok(voto);
    }

    @GetMapping("/resultado/{sessaoId}")
    public ResponseEntity<Map<String, Long>> resultado(@PathVariable Long sessaoId) {
        return ResponseEntity.ok(votoService.contabilizarVotos(sessaoId));
    }

    @Data
    public static class VotoRequest {
        private Long sessaoId;
        private String associadoId;
        private OpcaoVoto opcao;
    }
}
