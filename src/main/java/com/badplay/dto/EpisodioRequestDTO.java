package com.badplay.dto;

public class EpisodioRequestDTO {
    private String nome;
    private Integer numeroEpisodio;
    private Integer duracaoMinutos;
    private String trailerUrlYoutube;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getNumeroEpisodio() { return numeroEpisodio; }
    public void setNumeroEpisodio(Integer numeroEpisodio) { this.numeroEpisodio = numeroEpisodio; }
    public Integer getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(Integer duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }
    public String getTrailerUrlYoutube() { return trailerUrlYoutube; }
    public void setTrailerUrlYoutube(String trailerUrlYoutube) { this.trailerUrlYoutube = trailerUrlYoutube; }
}