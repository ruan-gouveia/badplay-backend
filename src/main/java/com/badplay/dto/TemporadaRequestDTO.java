package com.badplay.dto;

import java.util.List;

public class TemporadaRequestDTO {
    private Integer numeroTemporada;
    private List<EpisodioRequestDTO> episodios;

    public Integer getNumeroTemporada() { return numeroTemporada; }
    public void setNumeroTemporada(Integer numeroTemporada) { this.numeroTemporada = numeroTemporada; }
    public List<EpisodioRequestDTO> getEpisodios() { return episodios; }
    public void setEpisodios(List<EpisodioRequestDTO> episodios) { this.episodios = episodios; }
}