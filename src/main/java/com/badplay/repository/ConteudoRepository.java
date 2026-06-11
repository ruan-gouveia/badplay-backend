package com.badplay.repository;

import com.badplay.entity.Conteudo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConteudoRepository extends JpaRepository<Conteudo, Long> {

    @EntityGraph(attributePaths = {"generos"})
    @Query("""
            select distinct c
            from Conteudo c
            where lower(c.titulo) like lower(concat('%', :termo, '%'))
            order by c.titulo asc
            """)
    List<Conteudo> buscarPorTitulo(@Param("termo") String termo, Pageable pageable);
}