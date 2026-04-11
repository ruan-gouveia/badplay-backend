package com.badplay.dto;

import java.util.List;

public class SerieRequestDTO {
    private String titulo;
    private String descricao;
    private Integer anoLancamento;
    private List<TemporadaRequestDTO> temporadas;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Integer getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(Integer anoLancamento) { this.anoLancamento = anoLancamento; }
    public List<TemporadaRequestDTO> getTemporadas() { return temporadas; }
    public void setTemporadas(List<TemporadaRequestDTO> temporadas) { this.temporadas = temporadas; }
}