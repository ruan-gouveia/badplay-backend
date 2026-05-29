package com.badplay.repository;

import com.badplay.entity.ListaDesejo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListaDesejoRepository extends JpaRepository<ListaDesejo, Long> {
    List<ListaDesejo> findByUsuarioId(Long usuarioId);

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.data.jpa.repository.Query(value = "DELETE FROM tb_lista_conteudo WHERE conteudo_id = :conteudoId", nativeQuery = true)
    void deleteConteudoFromAllListas(@org.springframework.data.repository.query.Param("conteudoId") Long conteudoId);
}