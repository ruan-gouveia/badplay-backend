package com.badplay.dto;

public class HistoricoRequestDTO {
    private Long conteudoId;
    private Integer tempoAssistidoSegundos;

    public Long getConteudoId() { return conteudoId; }
    public void setConteudoId(Long conteudoId) { this.conteudoId = conteudoId; }
    public Integer getTempoAssistidoSegundos() { return tempoAssistidoSegundos; }
    public void setTempoAssistidoSegundos(Integer tempoAssistidoSegundos) { this.tempoAssistidoSegundos = tempoAssistidoSegundos; }
}