package com.badplay.service;

import com.badplay.dto.HistoricoRequestDTO;
import com.badplay.dto.HistoricoResponseDTO;
import com.badplay.entity.Conteudo;
import com.badplay.entity.HistoricoReproducao;
import com.badplay.entity.Usuario;
import com.badplay.repository.ConteudoRepository;
import com.badplay.repository.HistoricoReproducaoRepository;
import com.badplay.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricoService {

    private final HistoricoReproducaoRepository historicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ConteudoRepository conteudoRepository;

    public HistoricoService(HistoricoReproducaoRepository historicoRepository,
                            UsuarioRepository usuarioRepository,
                            ConteudoRepository conteudoRepository) {
        this.historicoRepository = historicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.conteudoRepository = conteudoRepository;
    }

    @Transactional
    public HistoricoResponseDTO registrarOuAtualizar(HistoricoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Conteudo conteudo = conteudoRepository.findById(dto.getConteudoId())
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado"));

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

        HistoricoReproducao salvo = historicoRepository.save(historico);
        return new HistoricoResponseDTO(salvo);
    }

    public List<HistoricoResponseDTO> buscarHistoricoDoUsuario(Long usuarioId) {
        return historicoRepository.findByUsuarioIdOrderByDataHoraVisualizacaoDesc(usuarioId)
                .stream()
                .map(HistoricoResponseDTO::new)
                .collect(Collectors.toList());
    }
}