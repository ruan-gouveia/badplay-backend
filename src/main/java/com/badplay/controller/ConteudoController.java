package com.badplay.controller;

import com.badplay.dto.ConteudoBuscaDTO;
import com.badplay.dto.ConteudoDetalhesDTO;
import com.badplay.repository.ConteudoRepository;
import com.badplay.repository.FilmeRepository;
import com.badplay.repository.SerieRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conteudos")
@CrossOrigin(origins = "*")
public class ConteudoController {

    private final ConteudoRepository conteudoRepository;
    private final FilmeRepository filmeRepository;
    private final SerieRepository serieRepository;

    public ConteudoController(
            ConteudoRepository conteudoRepository,
            FilmeRepository filmeRepository,
            SerieRepository serieRepository
    ) {
        this.conteudoRepository = conteudoRepository;
        this.filmeRepository = filmeRepository;
        this.serieRepository = serieRepository;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ConteudoBuscaDTO>> buscar(
            @RequestParam("q") String termo,
            @RequestParam(value = "limite", defaultValue = "30") int limite
    ) {
        String termoLimpo = termo == null ? "" : termo.trim();

        if (termoLimpo.length() < 2) {
            return ResponseEntity.ok(List.of());
        }

        int limiteSeguro = Math.min(Math.max(limite, 1), 50);

        List<ConteudoBuscaDTO> resultados = conteudoRepository
                .buscarPorTitulo(termoLimpo, PageRequest.of(0, limiteSeguro))
                .stream()
                .map(ConteudoBuscaDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<ConteudoDetalhesDTO> buscarPorId(
            @PathVariable("id") Long id
    ) {
        var filme = filmeRepository.findById(id);

        if (filme.isPresent()) {
            return ResponseEntity.ok(ConteudoDetalhesDTO.fromFilme(filme.get()));
        }

        var serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            return ResponseEntity.ok(ConteudoDetalhesDTO.fromSerie(serie.get()));
        }

        return ResponseEntity.notFound().build();
    }
}