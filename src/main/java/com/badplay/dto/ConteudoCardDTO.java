package com.badplay.dto;

import com.badplay.entity.Conteudo;
import com.badplay.entity.Filme;
import com.badplay.entity.Serie;
import com.badplay.entity.TipoPlano;

import java.util.List;

public record ConteudoCardDTO(
        Long id,
        String titulo,
        String descricao,
        Integer anoLancamento,
        String capaUrlMinio,
        TipoPlano planoMinimo,
        List<GeneroResumoDTO> generos,
        String tipo
) {
    public static ConteudoCardDTO fromEntity(Conteudo conteudo) {
        String tipoConteudo = "CONTEUDO";

        if (conteudo instanceof Filme) {
            tipoConteudo = "FILME";
        } else if (conteudo instanceof Serie) {
            tipoConteudo = "SERIE";
        }

        return new ConteudoCardDTO(
                conteudo.getId(),
                conteudo.getTitulo(),
                conteudo.getDescricao(),
                conteudo.getAnoLancamento(),
                conteudo.getCapaUrlMinio(),
                conteudo.getPlanoMinimo(),
                conteudo.getGeneros() == null
                        ? List.of()
                        : conteudo.getGeneros()
                        .stream()
                        .map(GeneroResumoDTO::fromEntity)
                        .toList(),
                tipoConteudo
        );
    }
}