package com.badplay.dto;

import com.badplay.entity.Episodio;

public record EpisodioResumoDTO(
        Long id,
        String nome,
        Integer numeroEpisodio,
        Integer duracaoMinutos,
        String trailerUrlYoutube
) {
    public static EpisodioResumoDTO fromEntity(Episodio episodio) {
        return new EpisodioResumoDTO(
                episodio.getId(),
                episodio.getNome(),
                episodio.getNumeroEpisodio(),
                episodio.getDuracaoMinutos(),
                episodio.getTrailerUrlYoutube()
        );
    }
}