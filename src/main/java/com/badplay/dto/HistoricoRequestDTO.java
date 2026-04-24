package com.badplay.dto;

public class HistoricoRequestDTO {
    private Long usuarioId;
    private Long conteudoId;
    private Integer tempoAssistidoSegundos;

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Long getConteudoId() { return conteudoId; }
    public void setConteudoId(Long conteudoId) { this.conteudoId = conteudoId; }
    public Integer getTempoAssistidoSegundos() { return tempoAssistidoSegundos; }
    public void setTempoAssistidoSegundos(Integer tempoAssistidoSegundos) { this.tempoAssistidoSegundos = tempoAssistidoSegundos; }
}