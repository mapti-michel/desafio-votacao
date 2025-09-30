package com.desafio_votacao.service;

import com.desafio_votacao.entity.*;
import com.desafio_votacao.repository.SessaoRepository;
import com.desafio_votacao.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VotoService {

    private final VotoRepository votoRepository;
    private final SessaoService sessaoService;
    private final CpfService cpfFacade;
    private final SessaoRepository sessaoRepository;

    public Voto registrarVoto(Long sessaoId, String associadoId, OpcaoVoto opcao) {
        // Verificar CPF válido
        if (!cpfFacade.cpfValido(associadoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CPF inválido");
        }

        // Verificar se CPF pode votar
        String statusCpf = cpfFacade.verificarCpf(associadoId);
        if ("UNABLE_TO_VOTE".equals(statusCpf)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Associado não pode votar");
        }

        // Verificar sessão
        Sessao sessao = sessaoService.buscarPorId(sessaoId);
        if (!sessao.isAberta()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sessão fechada");
        }

        // Verificar se associado já votou
        if (votoRepository.findBySessaoIdAndAssociadoId(sessaoId, associadoId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Associado já votou");
        }

        // Registrar voto
        Voto voto = Voto.builder()
                .associadoId(associadoId)
                .opcao(opcao)
                .sessao(sessao)
                .build();

        return votoRepository.save(voto);
    }

    // Contabilizar votos de uma sessão
    public Map<String, Long> contabilizarVotos(Long sessaoId) {
        Sessao sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada"));

        Map<OpcaoVoto, Long> resultado = sessao.getVotos().stream()
                .collect(Collectors.groupingBy(Voto::getOpcao, Collectors.counting()));

        // Converter Enum para String
        Map<String, Long> resultadoString = new HashMap<>();
        resultado.forEach((k,v) -> resultadoString.put(k.name(), v));

        return resultadoString;
    }
}

