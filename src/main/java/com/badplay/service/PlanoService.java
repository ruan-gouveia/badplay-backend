package com.badplay.service;

import com.badplay.entity.Plano;
import com.badplay.repository.PlanoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlanoService {
    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<Plano> listarTodos() {
        return planoRepository.findAll();
    }

    public Plano salvar(Plano plano) {
        return planoRepository.save(plano);
    }
}