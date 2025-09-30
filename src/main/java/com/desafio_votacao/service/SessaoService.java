package com.desafio_votacao.service;

import com.desafio_votacao.entity.Pauta;
import com.desafio_votacao.entity.Sessao;
import com.desafio_votacao.repository.SessaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessaoService {

    private final SessaoRepository sessaoRepository;

    public Sessao abrirSessao(Pauta pauta, Long duracaoMinutos) {
        Sessao sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setInicio(LocalDateTime.now());
        sessao.setFim(LocalDateTime.now().plusMinutes(duracaoMinutos != null ? duracaoMinutos : 1));

        return sessaoRepository.save(sessao);
    }

    public List<Sessao> listarSessoes() {
        return sessaoRepository.findAll();
    }

    public Sessao buscarPorId(Long id) {

        return sessaoRepository.findByIdWithVotos(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada"));
    }
}
