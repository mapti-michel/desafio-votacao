package com.desafio_votacao.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String associadoId; // CPF ou identificador único do associado

    @Enumerated(EnumType.STRING)
    private OpcaoVoto opcao;

    @ManyToOne
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

}
