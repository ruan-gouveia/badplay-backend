package com.badplay.controller;

import com.badplay.service.FileService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;

@Hidden
@RestController
@RequestMapping("/api/arquivos")
@CrossOrigin(origins = "*")
public class ArquivoController {

    private final FileService fileService;

    public ArquivoController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{nomeArquivo}")
    public ResponseEntity<InputStreamResource> visualizarArquivo(@PathVariable String nomeArquivo) {
        try {
            InputStream stream = fileService.buscarArquivo(nomeArquivo);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(stream));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}