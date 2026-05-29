package com.badplay.repository;

import com.badplay.entity.HistoricoReproducao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoricoReproducaoRepository extends JpaRepository<HistoricoReproducao, Long> {

    List<HistoricoReproducao> findByUsuarioIdOrderByDataHoraVisualizacaoDesc(Long usuarioId);

    Optional<HistoricoReproducao> findByUsuarioIdAndConteudoId(Long usuarioId, Long conteudoId);

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.data.jpa.repository.Query("DELETE FROM HistoricoReproducao h WHERE h.conteudo.id = :conteudoId")
    void deleteByConteudoId(@org.springframework.data.repository.query.Param("conteudoId") Long conteudoId);
}