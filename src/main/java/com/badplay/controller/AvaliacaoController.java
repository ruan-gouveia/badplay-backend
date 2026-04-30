package com.badplay.controller;

import com.badplay.dto.AvaliacaoRequestDTO;
import com.badplay.dto.AvaliacaoResponseDTO;
import com.badplay.service.AvaliacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> avaliar(@RequestBody AvaliacaoRequestDTO dto) {
        return ResponseEntity.ok(avaliacaoService.salvar(dto));
    }

    @GetMapping("/conteudo/{conteudoId}")
    public ResponseEntity<List<AvaliacaoResponseDTO>> listarPorConteudo(@PathVariable Long conteudoId) {
        return ResponseEntity.ok(avaliacaoService.buscarPorConteudo(conteudoId));
    }
}