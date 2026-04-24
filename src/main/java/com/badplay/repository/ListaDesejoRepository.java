package com.badplay.repository;

import com.badplay.entity.ListaDesejo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListaDesejoRepository extends JpaRepository<ListaDesejo, Long> {
    List<ListaDesejo> findByUsuarioId(Long usuarioId);
}