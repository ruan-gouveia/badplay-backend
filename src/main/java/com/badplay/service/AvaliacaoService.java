package com.badplay.service;

import com.badplay.dto.AvaliacaoRequestDTO;
import com.badplay.dto.AvaliacaoResponseDTO;
import com.badplay.entity.Avaliacao;
import com.badplay.entity.Conteudo;
import com.badplay.entity.Usuario;
import com.badplay.repository.AvaliacaoRepository;
import com.badplay.repository.ConteudoRepository;
import com.badplay.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ConteudoRepository conteudoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository,
                            UsuarioRepository usuarioRepository,
                            ConteudoRepository conteudoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.conteudoRepository = conteudoRepository;
    }

    @Transactional
    public AvaliacaoResponseDTO salvar(AvaliacaoRequestDTO dto) {
        if (dto.getNota() < 1.0 || dto.getNota() > 5.0) {
            throw new RuntimeException("A nota deve estar entre 1.0 e 5.0 estrelas.");
        }

        String emailLogado = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByEmail(emailLogado)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Conteudo conteudo = conteudoRepository.findById(dto.getConteudoId())
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado"));

        Avaliacao avaliacao = avaliacaoRepository
                .findByUsuarioIdAndConteudoId(usuario.getId(), conteudo.getId())
                .orElse(new Avaliacao());

        if (avaliacao.getId() == null) {
            avaliacao.setUsuario(usuario);
            avaliacao.setConteudo(conteudo);
        }

        avaliacao.setNota(dto.getNota());
        avaliacao.setComentarioSocial(dto.getComentarioSocial());

        Avaliacao salva = avaliacaoRepository.save(avaliacao);
        return new AvaliacaoResponseDTO(salva);
    }

    public List<AvaliacaoResponseDTO> buscarPorConteudo(Long conteudoId) {
        return avaliacaoRepository.findByConteudoId(conteudoId)
                .stream()
                .map(AvaliacaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletar(Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));

        String emailLogado = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
        if (!avaliacao.getUsuario().getEmail().equals(emailLogado)) {
            throw new RuntimeException("Acesso Negado: Esta avaliação não pertence a você.");
        }

        avaliacaoRepository.delete(avaliacao);
    }
}