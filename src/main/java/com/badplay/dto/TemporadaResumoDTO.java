package com.badplay.dto;

import com.badplay.entity.Episodio;
import com.badplay.entity.Temporada;

import java.util.Comparator;
import java.util.List;

public record TemporadaResumoDTO(
        Long id,
        Integer numeroTemporada,
        List<EpisodioResumoDTO> episodios
) {
    public static TemporadaResumoDTO fromEntity(Temporada temporada) {
        List<EpisodioResumoDTO> episodios = temporada.getEpisodios() == null
                ? List.of()
                : temporada.getEpisodios()
                .stream()
                .sorted(
                        Comparator.comparing(
                                Episodio::getNumeroEpisodio,
                                Comparator.nullsLast(Integer::compareTo)
                        )
                )
                .map(EpisodioResumoDTO::fromEntity)
                .toList();

        return new TemporadaResumoDTO(
                temporada.getId(),
                temporada.getNumeroTemporada(),
                episodios
        );
    }
}