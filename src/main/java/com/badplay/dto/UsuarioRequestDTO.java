package com.badplay.dto;

import com.badplay.entity.Role;
import com.badplay.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UsuarioRequestDTO {

    @NotBlank(message = "O nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Digite um e-mail válido (ex: seu@email.com)")
    private String email;

    @NotBlank(message = "A senha não pode estar vazia")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotNull(message = "A data de nascimento é obrigatória")
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Role getPerfil() {
        return perfil;
    }

    public void setPerfil(Role perfil) {
        this.perfil = perfil;
    }
}