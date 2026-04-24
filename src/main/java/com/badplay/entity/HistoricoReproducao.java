package com.badplay.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_historico_reproducao")
public class HistoricoReproducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conteudo_id", nullable = false)
    private Conteudo conteudo;

    @Column(nullable = false)
    private LocalDateTime dataHoraVisualizacao;

    private Integer tempoAssistidoSegundos = 0;

    public HistoricoReproducao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataHoraVisualizacao() {
        return dataHoraVisualizacao;
    }

    public void setDataHoraVisualizacao(LocalDateTime dataHoraVisualizacao) {
        this.dataHoraVisualizacao = dataHoraVisualizacao;
    }

    public Integer getTempoAssistidoSegundos() {
        return tempoAssistidoSegundos;
    }

    public void setTempoAssistidoSegundos(Integer tempoAssistidoSegundos) {
        this.tempoAssistidoSegundos = tempoAssistidoSegundos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoReproducao that = (HistoricoReproducao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}