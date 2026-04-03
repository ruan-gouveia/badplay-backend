package com.badplay.service;

import com.badplay.dto.UsuarioRequestDTO;
import com.badplay.dto.UsuarioResponseDTO;
import com.badplay.entity.Usuario;
import com.badplay.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioResponseDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioResponseDTO::new);
    }

    @Transactional
    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(dto.getEmail());

        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("Já existe um usuário cadastrado com este email: " + dto.getEmail());
        }

        Usuario usuario = dto.toEntity();

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuarioSalvo);
    }

    @Transactional
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}