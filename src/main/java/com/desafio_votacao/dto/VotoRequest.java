package com.desafio_votacao.dto;

import com.desafio_votacao.entity.OpcaoVoto;
import lombok.Data;

@Data
public class VotoRequest {
    private Long sessaoId;
    private String associadoId; // CPF ou identificador único
    private OpcaoVoto opcao;



}
