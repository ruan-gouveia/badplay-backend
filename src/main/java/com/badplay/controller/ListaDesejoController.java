package com.badplay.controller;

import com.badplay.dto.ListaDesejoRequestDTO;
import com.badplay.dto.ListaDesejoResponseDTO;
import com.badplay.service.ListaDesejoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listas")
@CrossOrigin(origins = "*")
public class ListaDesejoController {

    private final ListaDesejoService listaService;

    public ListaDesejoController(ListaDesejoService listaService) {
        this.listaService = listaService;
    }

    @PostMapping
    public ResponseEntity<ListaDesejoResponseDTO> criar(@RequestBody ListaDesejoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(listaService.criarLista(dto));
    }

    @PutMapping("/{listaId}/adicionar/{conteudoId}")
    public ResponseEntity<ListaDesejoResponseDTO> adicionarConteudo(
            @PathVariable Long listaId,
            @PathVariable Long conteudoId) {
        return ResponseEntity.ok(listaService.adicionarConteudo(listaId, conteudoId));
    }

    @GetMapping("/minhas")
    public ResponseEntity<List<ListaDesejoResponseDTO>> buscarMinhasListas() {
        return ResponseEntity.ok(listaService.buscarMinhasListas());
    }
}