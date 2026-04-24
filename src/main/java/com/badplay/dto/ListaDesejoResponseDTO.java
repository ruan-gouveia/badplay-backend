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
    private List<String> nomesConteudos;
    public ListaDesejoResponseDTO(ListaDesejo lista) {
        this.id = lista.getId();
        this.nome = lista.getNome();
        this.dataCriacao = lista.getDataCriacao();
        this.nomesConteudos = lista.getConteudos().stream()
                .map(Conteudo::getTitulo)
                .collect(Collectors.toList());
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public LocalDate getDataCriacao() { return dataCriacao; }
    public List<String> getNomesConteudos() { return nomesConteudos; }
}