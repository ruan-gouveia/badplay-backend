package com.badplay.service;

import com.badplay.dto.AssinaturaRequestDTO;
import com.badplay.dto.AssinaturaResponseDTO;
import com.badplay.entity.Assinatura;
import com.badplay.entity.Plano;
import com.badplay.entity.StatusAssinatura;
import com.badplay.entity.Usuario;
import com.badplay.repository.AssinaturaRepository;
import com.badplay.repository.PlanoRepository;
import com.badplay.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlanoRepository planoRepository;

    public AssinaturaService(AssinaturaRepository assinaturaRepository,
                             UsuarioRepository usuarioRepository,
                             PlanoRepository planoRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.usuarioRepository = usuarioRepository;
        this.planoRepository = planoRepository;
    }

    @Transactional
    public AssinaturaResponseDTO assinar(AssinaturaRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Plano plano = planoRepository.findById(dto.getPlanoId())
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));

        Assinatura assinatura = new Assinatura();
        assinatura.setUsuario(usuario);
        assinatura.setPlano(plano);
        assinatura.setDataInicio(LocalDate.now());
        assinatura.setDataFim(LocalDate.now().plusDays(30));
        assinatura.setStatus(StatusAssinatura.ATIVA);

        Assinatura assinaturaSalva = assinaturaRepository.save(assinatura);

        return new AssinaturaResponseDTO(assinaturaSalva);
    }
}