package com.badplay.dto;

public class EstatisticasResponseDTO {
    private long filmesCadastrados;
    private long seriesCadastradas;
    private long usuariosAtivos;
    private long reproducoesHoje;

    public EstatisticasResponseDTO(long filmesCadastrados, long seriesCadastradas, long usuariosAtivos, long reproducoesHoje) {
        this.filmesCadastrados = filmesCadastrados;
        this.seriesCadastradas = seriesCadastradas;
        this.usuariosAtivos = usuariosAtivos;
        this.reproducoesHoje = reproducoesHoje;
    }

    public long getFilmesCadastrados() { return filmesCadastrados; }
    public long getSeriesCadastradas() { return seriesCadastradas; }
    public long getUsuariosAtivos() { return usuariosAtivos; }
    public long getReproducoesHoje() { return reproducoesHoje; }
}