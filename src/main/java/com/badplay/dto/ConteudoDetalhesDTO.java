package com.badplay.dto;

import com.badplay.entity.Filme;
import com.badplay.entity.Serie;
import com.badplay.entity.Temporada;
import com.badplay.entity.TipoPlano;

import java.util.Comparator;
import java.util.List;

public record ConteudoDetalhesDTO(
        Long id,
        String titulo,
        String descricao,
        Integer anoLancamento,
        String capaUrlMinio,
        TipoPlano planoMinimo,
        List<GeneroResumoDTO> generos,
        String tipo,
        Integer duracaoMinutos,
        String trailerUrlYoutube,
        List<TemporadaResumoDTO> temporadas
) {
    public static ConteudoDetalhesDTO fromFilme(Filme filme) {
        return new ConteudoDetalhesDTO(
                filme.getId(),
                filme.getTitulo(),
                filme.getDescricao(),
                filme.getAnoLancamento(),
                filme.getCapaUrlMinio(),
                filme.getPlanoMinimo(),
                filme.getGeneros() == null
                        ? List.of()
                        : filme.getGeneros()
                        .stream()
                        .map(GeneroResumoDTO::fromEntity)
                        .toList(),
                "FILME",
                filme.getDuracaoMinutos(),
                filme.getTrailerUrlYoutube(),
                List.of()
        );
    }

    public static ConteudoDetalhesDTO fromSerie(Serie serie) {
        List<TemporadaResumoDTO> temporadas = serie.getTemporadas() == null
                ? List.of()
                : serie.getTemporadas()
                .stream()
                .sorted(
                        Comparator.comparing(
                                Temporada::getNumeroTemporada,
                                Comparator.nullsLast(Integer::compareTo)
                        )
                )
                .map(TemporadaResumoDTO::fromEntity)
                .toList();

        return new ConteudoDetalhesDTO(
                serie.getId(),
                serie.getTitulo(),
                serie.getDescricao(),
                serie.getAnoLancamento(),
                serie.getCapaUrlMinio(),
                serie.getPlanoMinimo(),
                serie.getGeneros() == null
                        ? List.of()
                        : serie.getGeneros()
                        .stream()
                        .map(GeneroResumoDTO::fromEntity)
                        .toList(),
                "SERIE",
                null,
                serie.getTrailerUrlYoutube(),
                temporadas
        );
    }
}