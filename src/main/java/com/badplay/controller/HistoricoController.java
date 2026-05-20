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
        return ResponseEntity.ok(historicoService.registrarOuAtualizar(dto));
    }

    @GetMapping("/meu-historico")
    public ResponseEntity<List<HistoricoResponseDTO>> listarMeuHistorico() {
        return ResponseEntity.ok(historicoService.buscarMeuHistorico());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        historicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}