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
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ListaDesejo lista = new ListaDesejo();
        lista.setNome(dto.getNome());
        lista.setUsuario(usuario);
        lista.setDataCriacao(LocalDate.now());

        ListaDesejo salva = listaRepository.save(lista);
        return new ListaDesejoResponseDTO(salva);
    }

    @Transactional
    public ListaDesejoResponseDTO adicionarConteudo(Long listaId, Long conteudoId) {
        ListaDesejo lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        Conteudo conteudo = conteudoRepository.findById(conteudoId)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado"));

        if (!lista.getConteudos().contains(conteudo)) {
            lista.getConteudos().add(conteudo);
            listaRepository.save(lista);
        }

        return new ListaDesejoResponseDTO(lista);
    }

    public List<ListaDesejoResponseDTO> buscarPorUsuario(Long usuarioId) {
        return listaRepository.findByUsuarioId(usuarioId).stream()
                .map(ListaDesejoResponseDTO::new)
                .collect(Collectors.toList());
    }
}