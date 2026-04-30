package com.badplay.service;

import com.badplay.dto.HistoricoRequestDTO;
import com.badplay.dto.HistoricoResponseDTO;
import com.badplay.entity.*;
import com.badplay.repository.AssinaturaRepository;
import com.badplay.repository.ConteudoRepository;
import com.badplay.repository.HistoricoReproducaoRepository;
import com.badplay.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricoService {

    private final HistoricoReproducaoRepository historicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ConteudoRepository conteudoRepository;
    private final AssinaturaRepository assinaturaRepository;

    public HistoricoService(HistoricoReproducaoRepository historicoRepository,
                            UsuarioRepository usuarioRepository,
                            ConteudoRepository conteudoRepository,
                            AssinaturaRepository assinaturaRepository) {
        this.historicoRepository = historicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.conteudoRepository = conteudoRepository;
        this.assinaturaRepository = assinaturaRepository;
    }

    @Transactional
    public HistoricoResponseDTO registrarOuAtualizar(HistoricoRequestDTO dto) {
        String emailLogado = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByEmail(emailLogado)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Conteudo conteudo = conteudoRepository.findById(dto.getConteudoId())
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado"));

        Assinatura assinatura = assinaturaRepository
                .findFirstByUsuarioIdAndStatus(usuario.getId(), StatusAssinatura.ATIVA)
                .orElseThrow(() -> new RuntimeException("Acesso Negado: Você não possui uma assinatura ativa!"));

        if (assinatura.getPlano().getTipo().ordinal() < conteudo.getPlanoMinimo().ordinal()) {
            throw new RuntimeException("Acesso Negado: Este conteúdo exige o plano " +
                    conteudo.getPlanoMinimo() + ". Faça o upgrade da sua assinatura.");
        }

        HistoricoReproducao historico = historicoRepository
                .findByUsuarioIdAndConteudoId(usuario.getId(), conteudo.getId())
                .orElse(new HistoricoReproducao());

        if (historico.getId() == null) {
            historico.setUsuario(usuario);
            historico.setConteudo(conteudo);
        }

        historico.setDataHoraVisualizacao(LocalDateTime.now());
        if (dto.getTempoAssistidoSegundos() != null) {
            historico.setTempoAssistidoSegundos(dto.getTempoAssistidoSegundos());
        }

        return new HistoricoResponseDTO(historicoRepository.save(historico));
    }

    public List<HistoricoResponseDTO> buscarMeuHistorico() {
        String emailLogado = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByEmail(emailLogado).orElseThrow();

        return historicoRepository.findByUsuarioIdOrderByDataHoraVisualizacaoDesc(usuario.getId())
                .stream()
                .map(HistoricoResponseDTO::new)
                .collect(Collectors.toList());
    }
}