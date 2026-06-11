package com.badplay.dto;

import com.badplay.entity.Conteudo;
import com.badplay.entity.Filme;
import com.badplay.entity.Serie;
import com.badplay.entity.TipoPlano;

public record ConteudoBuscaDTO(
        Long id,
        String titulo,
        String descricao,
        Integer anoLancamento,
        String capaUrlMinio,
        TipoPlano planoMinimo,
        String tipo
) {
    public static ConteudoBuscaDTO fromEntity(Conteudo conteudo) {
        String tipoConteudo = "CONTEUDO";

        if (conteudo instanceof Filme) {
            tipoConteudo = "FILME";
        } else if (conteudo instanceof Serie) {
            tipoConteudo = "SERIE";
        }

        return new ConteudoBuscaDTO(
                conteudo.getId(),
                conteudo.getTitulo(),
                conteudo.getDescricao(),
                conteudo.getAnoLancamento(),
                conteudo.getCapaUrlMinio(),
                conteudo.getPlanoMinimo(),
                tipoConteudo
        );
    }
}