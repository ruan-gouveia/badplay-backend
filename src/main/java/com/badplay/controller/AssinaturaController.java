package com.badplay.controller;

import com.badplay.dto.AssinaturaRequestDTO;
import com.badplay.dto.AssinaturaResponseDTO;
import com.badplay.service.AssinaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assinaturas")
@CrossOrigin(origins = "*")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    public AssinaturaController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }

    @PostMapping
    public ResponseEntity<AssinaturaResponseDTO> assinar(@RequestBody AssinaturaRequestDTO dto) {
        try {
            AssinaturaResponseDTO novaAssinatura = assinaturaService.assinar(dto);
            return ResponseEntity.ok(novaAssinatura);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}