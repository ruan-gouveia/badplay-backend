package com.badplay.repository;

import com.badplay.entity.Conteudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConteudoRepository extends JpaRepository<Conteudo, Long> {

    List<Conteudo> findByTituloContainingIgnoreCase(String titulo);
}