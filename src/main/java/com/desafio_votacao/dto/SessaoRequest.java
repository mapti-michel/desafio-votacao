package com.desafio_votacao.dto;

public class SessaoRequest {
    private Long pautaId;
    private Long duracaoEmMinutos;




    public Long getPautaId() { return pautaId; }
    public void setPautaId(Long pautaId) { this.pautaId = pautaId; }

    public Long getDuracaoEmMinutos() { return duracaoEmMinutos; }
    public void setDuracaoEmMinutos(Long duracaoEmMinutos) { this.duracaoEmMinutos = duracaoEmMinutos; }
}
