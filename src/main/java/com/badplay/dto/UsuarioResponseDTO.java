package com.badplay.dto;

import com.badplay.entity.Role;
import com.badplay.entity.Usuario;
import java.time.LocalDate;

public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private Role perfil;

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.dataNascimento = usuario.getDataNascimento();
        this.perfil = usuario.getPerfil();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public Role getPerfil() { return perfil; }
}