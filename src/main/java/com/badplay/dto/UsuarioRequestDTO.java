package com.badplay.dto;

import com.badplay.entity.Role;
import com.badplay.entity.Usuario;
import java.time.LocalDate;

public class UsuarioRequestDTO {

    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private Role perfil;

    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        usuario.setDataNascimento(this.dataNascimento);
        if (this.perfil != null) {
            usuario.setPerfil(this.perfil);
        }
        return usuario;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public Role getPerfil() { return perfil; }
    public void setPerfil(Role perfil) { this.perfil = perfil; }
}