package com.badplay.service;

import com.badplay.dto.ListaDesejoRequestDTO;
import com.badplay.dto.ListaDesejoResponseDTO;
import com.badplay.entity.Conteudo;
import com.badplay.entity.ListaDesejo;
import com.badplay.entity.Usuario;
import com.badplay.repository.ConteudoRepository;
import com.badplay.repository.ListaDesejoRepository;
import com.badplay.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListaDesejoService {

    private final ListaDesejoRepository listaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ConteudoRepository conteudoRepository;

    public ListaDesejoService(ListaDesejoRepository listaRepository,
                              UsuarioRepository usuarioRepository,
                              ConteudoRepository conteudoRepository) {
        this.listaRepository = listaRepository;
        this.usuarioRepository = usuarioRepository;
        this.conteudoRepository = conteudoRepository;
    }

    @Transactional
    public ListaDesejoResponseDTO criarLista(ListaDesejoRequestDTO dto) {
        String emailLogado = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByEmail(emailLogado)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ListaDesejo lista = new ListaDesejo();
        lista.setNome(dto.getNome());
        lista.setUsuario(usuario);
        lista.setDataCriacao(LocalDate.now());

        return new ListaDesejoResponseDTO(listaRepository.save(lista));
    }

    @Transactional
    public ListaDesejoResponseDTO adicionarConteudo(Long listaId, Long conteudoId) {
        ListaDesejo lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        String emailLogado = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!lista.getUsuario().getEmail().equals(emailLogado)) {
            throw new RuntimeException("Acesso Negado: Você não é o dono desta lista!");
        }

        Conteudo conteudo = conteudoRepository.findById(conteudoId)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado"));

        if (!lista.getConteudos().contains(conteudo)) {
            lista.getConteudos().add(conteudo);
            listaRepository.save(lista);
        }

        return new ListaDesejoResponseDTO(lista);
    }

    public List<ListaDesejoResponseDTO> buscarMinhasListas() {
        String emailLogado = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByEmail(emailLogado).orElseThrow();

        return listaRepository.findByUsuarioId(usuario.getId()).stream()
                .map(ListaDesejoResponseDTO::new)
                .collect(Collectors.toList());
    }

    private void validarDonoDaLista(ListaDesejo lista) {
        String emailLogado = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
        if (!lista.getUsuario().getEmail().equals(emailLogado)) {
            throw new RuntimeException("Acesso Negado: Você não é o dono desta lista.");
        }
    }

    @Transactional
    public ListaDesejoResponseDTO renomearLista(Long listaId, ListaDesejoRequestDTO dto) {
        ListaDesejo lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));
        validarDonoDaLista(lista);

        lista.setNome(dto.getNome());
        return new ListaDesejoResponseDTO(listaRepository.save(lista));
    }

    @Transactional
    public ListaDesejoResponseDTO removerConteudo(Long listaId, Long conteudoId) {
        ListaDesejo lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));
        validarDonoDaLista(lista);

        Conteudo conteudo = conteudoRepository.findById(conteudoId)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado"));

        lista.getConteudos().remove(conteudo);
        return new ListaDesejoResponseDTO(listaRepository.save(lista));
    }

    @Transactional
    public void deletarLista(Long listaId) {
        ListaDesejo lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));
        validarDonoDaLista(lista);
        listaRepository.delete(lista);
    }
}