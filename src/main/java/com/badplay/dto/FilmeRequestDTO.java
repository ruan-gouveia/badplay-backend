package com.badplay.dto;

import java.util.List;

public class FilmeRequestDTO {
    private String titulo;
    private String descricao;
    private Integer anoLancamento;
    private Integer duracaoMinutos;
    private String trailerUrlYoutube;
    private List<Long> generosIds;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Integer getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(Integer anoLancamento) { this.anoLancamento = anoLancamento; }
    public Integer getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(Integer duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }
    public String getTrailerUrlYoutube() { return trailerUrlYoutube; }
    public void setTrailerUrlYoutube(String trailerUrlYoutube) { this.trailerUrlYoutube = trailerUrlYoutube; }
    public List<Long> getGenerosIds() { return generosIds; }
    public void setGenerosIds(List<Long> generosIds) { this.generosIds = generosIds; }
}