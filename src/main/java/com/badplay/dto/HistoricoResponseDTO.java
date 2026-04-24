package com.badplay.dto;

import com.badplay.entity.HistoricoReproducao;
import java.time.LocalDateTime;

public class HistoricoResponseDTO {
    private Long id;
    private Long conteudoId;
    private String conteudoTitulo;
    private String capaUrlMinio;
    private LocalDateTime dataHoraVisualizacao;
    private Integer tempoAssistidoSegundos;

    public HistoricoResponseDTO(HistoricoReproducao historico) {
        this.id = historico.getId();
        this.conteudoId = historico.getConteudo().getId();
        this.conteudoTitulo = historico.getConteudo().getTitulo();
        this.capaUrlMinio = historico.getConteudo().getCapaUrlMinio();
        this.dataHoraVisualizacao = historico.getDataHoraVisualizacao();
        this.tempoAssistidoSegundos = historico.getTempoAssistidoSegundos();
    }

    public Long getId() { return id; }
    public Long getConteudoId() { return conteudoId; }
    public String getConteudoTitulo() { return conteudoTitulo; }
    public String getCapaUrlMinio() { return capaUrlMinio; }
    public LocalDateTime getDataHoraVisualizacao() { return dataHoraVisualizacao; }
    public Integer getTempoAssistidoSegundos() { return tempoAssistidoSegundos; }
}