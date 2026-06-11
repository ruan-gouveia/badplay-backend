package com.badplay.dto;

import com.badplay.entity.Genero;

public record GeneroResumoDTO(
        Long id,
        String nome
) {
    public static GeneroResumoDTO fromEntity(Genero genero) {
        return new GeneroResumoDTO(
                genero.getId(),
                genero.getNome()
        );
    }
}