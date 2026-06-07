package com.badplay.repository;

import com.badplay.entity.HistoricoReproducao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface HistoricoReproducaoRepository extends JpaRepository<HistoricoReproducao, Long> {

    List<HistoricoReproducao> findByUsuarioIdOrderByDataHoraVisualizacaoDesc(Long usuarioId);

    Optional<HistoricoReproducao> findByUsuarioIdAndConteudoId(Long usuarioId, Long conteudoId);

    @Modifying
    @Query("DELETE FROM HistoricoReproducao h WHERE h.conteudo.id = :conteudoId")
    void deleteByConteudoId(@Param("conteudoId") Long conteudoId);

    @Query("SELECT COUNT(h) FROM HistoricoReproducao h WHERE h.dataHoraVisualizacao >= :inicio AND h.dataHoraVisualizacao < :fim")
    long countByDataHoraVisualizacaoBetween(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}