package com.badplay.controller;

import com.badplay.entity.Plano;
import com.badplay.service.PlanoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/planos")
@CrossOrigin(origins = "*")
public class PlanoController {
    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @GetMapping
    public ResponseEntity<List<Plano>> listar() {
        return ResponseEntity.ok(planoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Plano> criar(@RequestBody Plano plano) {
        return ResponseEntity.ok(planoService.salvar(plano));
    }
}