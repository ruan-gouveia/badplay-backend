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
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(listaService.criarLista(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{listaId}/adicionar/{conteudoId}")
    public ResponseEntity<ListaDesejoResponseDTO> adicionarConteudo(
            @PathVariable Long listaId,
            @PathVariable Long conteudoId) {
        try {
            return ResponseEntity.ok(listaService.adicionarConteudo(listaId, conteudoId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ListaDesejoResponseDTO>> buscarDoUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(listaService.buscarPorUsuario(usuarioId));
    }
}