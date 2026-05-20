package com.badplay.repository;

import com.badplay.entity.Assinatura;
import com.badplay.entity.StatusAssinatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {

    Optional<Assinatura> findFirstByUsuarioIdAndStatus(Long usuarioId, StatusAssinatura status);

    List<Assinatura> findAllByUsuarioIdAndStatus(Long usuarioId, StatusAssinatura status);
}