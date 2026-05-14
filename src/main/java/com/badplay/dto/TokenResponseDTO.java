package com.badplay.dto;

public class TokenResponseDTO {
    private String token;
    private Long usuarioId;
    private String nome;
    private String perfil;

    public TokenResponseDTO(String token, Long usuarioId, String nome, String perfil) {
        this.token = token;
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.perfil = perfil;
    }

    public String getToken() { return token; }
    public Long getUsuarioId() { return usuarioId; }
    public String getNome() { return nome; }
    public String getPerfil() { return perfil; }
}