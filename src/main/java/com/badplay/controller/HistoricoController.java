package com.badplay.controller;

import com.badplay.dto.HistoricoRequestDTO;
import com.badplay.dto.HistoricoResponseDTO;
import com.badplay.service.HistoricoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historico")
@CrossOrigin(origins = "*")
public class HistoricoController {

    private final HistoricoService historicoService;

    public HistoricoController(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }

    @PostMapping
    public ResponseEntity<HistoricoResponseDTO> registrar(@RequestBody HistoricoRequestDTO dto) {
        try {
            return ResponseEntity.ok(historicoService.registrarOuAtualizar(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<HistoricoResponseDTO>> listar(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(historicoService.buscarHistoricoDoUsuario(usuarioId));
    }
}