package com.badplay.controller;

import com.badplay.dto.SerieRequestDTO;
import com.badplay.entity.Serie;
import com.badplay.service.SerieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/series")
@CrossOrigin(origins = "*")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Serie> criarSerie(
            @RequestPart("dados") SerieRequestDTO dto,
            @RequestPart("capa") MultipartFile capa
    ) {
        try {
            Serie serieSalva = serieService.salvar(dto, capa);
            return ResponseEntity.status(HttpStatus.CREATED).body(serieSalva);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}