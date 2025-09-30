package com.desafio_votacao.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CpfService {

    private final Random random = new Random();

    public String verificarCpf(String cpf) {
        // 30% de chance de retornar UNABLE_TO_VOTE
        if (random.nextInt(100) < 30) {
            return "UNABLE_TO_VOTE";
        }
        return "ABLE_TO_VOTE";
    }

    public boolean cpfValido(String cpf) {
        // 10% de chance de CPF inválido
        return random.nextInt(100) >= 10;
    }
}

