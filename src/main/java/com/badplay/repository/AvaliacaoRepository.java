package com.badplay.repository;

import com.badplay.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByConteudoId(Long conteudoId);

    Optional<Avaliacao> findByUsuarioIdAndConteudoId(Long usuarioId, Long conteudoId);

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.data.jpa.repository.Query("DELETE FROM Avaliacao a WHERE a.conteudo.id = :conteudoId")
    void deleteByConteudoId(@org.springframework.data.repository.query.Param("conteudoId") Long conteudoId);
}