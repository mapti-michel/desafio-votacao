package com.desafio_votacao.entity;

import com.desafio_votacao.entity.Pauta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @OneToMany(mappedBy = "sessao", fetch = FetchType.EAGER)
    private List<Voto> votos;

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public boolean isAberta() {
        LocalDateTime agora = LocalDateTime.now();
        return inicio.isBefore(agora) && fim.isAfter(agora);
    }
}
