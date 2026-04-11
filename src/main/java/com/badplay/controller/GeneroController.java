package com.badplay.controller;

import com.badplay.entity.Genero;
import com.badplay.service.GeneroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
@CrossOrigin(origins = "*")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public ResponseEntity<List<Genero>> listar() {
        return ResponseEntity.ok(generoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Genero> criar(@RequestBody Genero genero) {
        try {
            Genero salvo = generoService.salvar(genero);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}