package com.badplay.repository;

import com.badplay.entity.Plano;
import com.badplay.entity.TipoPlano;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PlanoRepository extends JpaRepository<Plano, Long> {
    Optional<Plano> findByTipo(TipoPlano tipo);
}