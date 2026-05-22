package com.badplay.dto;

import com.badplay.entity.Conteudo;
import com.badplay.entity.ListaDesejo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ListaDesejoResponseDTO {
    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private List<ConteudoListaDTO> conteudos;

    public ListaDesejoResponseDTO(ListaDesejo lista) {
        this.id = lista.getId();
        this.nome = lista.getNome();
        this.dataCriacao = lista.getDataCriacao();
        this.conteudos = lista.getConteudos().stream()
                .map(ConteudoListaDTO::new)
                .collect(Collectors.toList());
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public LocalDate getDataCriacao() { return dataCriacao; }
    public List<ConteudoListaDTO> getConteudos() { return conteudos; }

    public static class ConteudoListaDTO {
        private Long id;
        private String titulo;
        private String capaUrlMinio;
        private String planoMinimo;

        public ConteudoListaDTO(Conteudo conteudo) {
            this.id = conteudo.getId();
            this.titulo = conteudo.getTitulo();
            this.capaUrlMinio = conteudo.getCapaUrlMinio();
            this.planoMinimo = conteudo.getPlanoMinimo() != null ? conteudo.getPlanoMinimo().name() : "BASICO";
        }

        public Long getId() { return id; }
        public String getTitulo() { return titulo; }
        public String getCapaUrlMinio() { return capaUrlMinio; }
        public String getPlanoMinimo() { return planoMinimo; }
    }
}