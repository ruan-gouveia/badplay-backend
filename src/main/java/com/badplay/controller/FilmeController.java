package com.badplay.controller;

import com.badplay.dto.FilmeRequestDTO;
import com.badplay.entity.Filme;
import com.badplay.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/filmes")
@CrossOrigin(origins = "*")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Filme> criarFilme(
            @RequestPart("dados") FilmeRequestDTO dto,
            @RequestPart("capa") MultipartFile capa
    ) {
        Filme filmeSalvo = filmeService.salvar(dto, capa);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeSalvo);
    }
}