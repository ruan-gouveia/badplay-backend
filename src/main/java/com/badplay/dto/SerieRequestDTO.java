package com.badplay.dto;

import java.util.List;
import com.badplay.entity.TipoPlano;

public class SerieRequestDTO {
    private String titulo;
    private String descricao;
    private Integer anoLancamento;
    private List<TemporadaRequestDTO> temporadas;
    private List<Long> generosIds;
    private TipoPlano planoMinimo;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Integer getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(Integer anoLancamento) { this.anoLancamento = anoLancamento; }
    public List<TemporadaRequestDTO> getTemporadas() { return temporadas; }
    public void setTemporadas(List<TemporadaRequestDTO> temporadas) { this.temporadas = temporadas; }
    public List<Long> getGenerosIds() { return generosIds; }
    public void setGenerosIds(List<Long> generosIds) { this.generosIds = generosIds; }
    public TipoPlano getPlanoMinimo() { return planoMinimo; }
    public void setPlanoMinimo(TipoPlano planoMinimo) { this.planoMinimo = planoMinimo; }
}