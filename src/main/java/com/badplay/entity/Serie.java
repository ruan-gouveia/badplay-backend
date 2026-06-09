package com.badplay.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_serie")
public class Serie extends Conteudo {

    private String trailerUrlYoutube;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Temporada> temporadas = new ArrayList<>();

    public Serie() {
    }

    public String getTrailerUrlYoutube() {
        return trailerUrlYoutube;
    }

    public void setTrailerUrlYoutube(String trailerUrlYoutube) {
        this.trailerUrlYoutube = trailerUrlYoutube;
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }
}