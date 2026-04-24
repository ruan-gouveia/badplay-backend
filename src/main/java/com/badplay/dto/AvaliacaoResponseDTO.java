package com.badplay.dto;

import com.badplay.entity.Avaliacao;

public class AvaliacaoResponseDTO {
    private Long id;
    private Double nota;
    private String comentarioSocial;
    private String nomeUsuario;

    public AvaliacaoResponseDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.nota = avaliacao.getNota();
        this.comentarioSocial = avaliacao.getComentarioSocial();
        this.nomeUsuario = avaliacao.getUsuario().getNome();
    }

    public Long getId() { return id; }
    public Double getNota() { return nota; }
    public String getComentarioSocial() { return comentarioSocial; }
    public String getNomeUsuario() { return nomeUsuario; }
}