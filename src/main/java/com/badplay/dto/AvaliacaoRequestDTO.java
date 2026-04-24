package com.badplay.dto;

public class AvaliacaoRequestDTO {
    private Long usuarioId;
    private Long conteudoId;
    private Double nota;
    private String comentarioSocial;

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Long getConteudoId() { return conteudoId; }
    public void setConteudoId(Long conteudoId) { this.conteudoId = conteudoId; }
    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }
    public String getComentarioSocial() { return comentarioSocial; }
    public void setComentarioSocial(String comentarioSocial) { this.comentarioSocial = comentarioSocial; }
}