package com.badplay.repository;

import com.badplay.entity.Serie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

    @EntityGraph(attributePaths = {"generos"})
    List<Serie> findAll();
}