package com.badplay.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_filme")
public class Filme extends Conteudo {

    private Integer duracaoMinutos;

    private String trailerUrlYoutube;

    public Filme() {
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Integer duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public String getTrailerUrlYoutube() {
        return trailerUrlYoutube;
    }

    public void setTrailerUrlYoutube(String trailerUrlYoutube) {
        this.trailerUrlYoutube = trailerUrlYoutube;
    }
}