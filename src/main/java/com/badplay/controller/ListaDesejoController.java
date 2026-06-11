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
            @PathVariable("conteudoId") Long conteudoId) {
        return ResponseEntity.ok(listaService.adicionarConteudo(listaId, conteudoId));
    }

    @GetMapping("/minhas")
    public ResponseEntity<List<ListaDesejoResponseDTO>> buscarMinhasListas() {
        return ResponseEntity.ok(listaService.buscarMinhasListas());
    }

    @PutMapping("/{listaId}")
    public ResponseEntity<ListaDesejoResponseDTO> renomearLista(@PathVariable Long listaId, @RequestBody ListaDesejoRequestDTO dto) {
        try {
            return ResponseEntity.ok(listaService.renomearLista(listaId, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{listaId}/remover/{conteudoId}")
    public ResponseEntity<ListaDesejoResponseDTO> removerConteudo(@PathVariable Long listaId, @PathVariable Long conteudoId) {
        try {
            return ResponseEntity.ok(listaService.removerConteudo(listaId, conteudoId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{listaId}")
    public ResponseEntity<Void> deletarLista(@PathVariable Long listaId) {
        try {
            listaService.deletarLista(listaId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}