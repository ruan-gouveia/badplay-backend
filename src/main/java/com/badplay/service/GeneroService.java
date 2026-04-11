package com.badplay.service;

import com.badplay.entity.Genero;
import com.badplay.repository.GeneroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public List<Genero> listarTodos() {
        return generoRepository.findAll();
    }

    public Genero salvar(Genero genero) {
        Optional<Genero> existente = generoRepository.findByNomeIgnoreCase(genero.getNome());
        if (existente.isPresent()) {
            throw new RuntimeException("Gênero já existe!");
        }
        return generoRepository.save(genero);
    }

    public List<Genero> buscarPorIds(List<Long> ids) {
        return generoRepository.findAllById(ids);
    }
}