package com.badplay.controller;

import com.badplay.dto.EstatisticasResponseDTO;
import com.badplay.service.EstatisticasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estatisticas")
@CrossOrigin(origins = "*")
public class EstatisticasController {

    private final EstatisticasService estatisticasService;

    public EstatisticasController(EstatisticasService estatisticasService) {
        this.estatisticasService = estatisticasService;
    }

    @GetMapping
    public ResponseEntity<EstatisticasResponseDTO> obterDados() {
        return ResponseEntity.ok(estatisticasService.buscarEstatisticas());
    }
}