package com.badplay.controller;

import com.badplay.dto.LoginRequestDTO;
import com.badplay.dto.TokenResponseDTO;
import com.badplay.entity.Usuario;
import com.badplay.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
            Authentication auth = authenticationManager.authenticate(usernamePassword);

            Usuario usuarioLogado = (Usuario) auth.getPrincipal();
            String tokenJWT = tokenService.gerarToken(usuarioLogado);

            return ResponseEntity.ok(new TokenResponseDTO(
                    tokenJWT,
                    usuarioLogado.getId(),
                    usuarioLogado.getNome(),
                    usuarioLogado.getPerfil().name()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}