package com.badplay.service;

import com.badplay.dto.FilmeRequestDTO;
import com.badplay.entity.Filme;
import com.badplay.repository.FilmeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final FileService fileService;

    public FilmeService(FilmeRepository filmeRepository, FileService fileService) {
        this.filmeRepository = filmeRepository;
        this.fileService = fileService;
    }

    @Transactional
    public Filme salvar(FilmeRequestDTO dto, MultipartFile capa) {
        String nomeCapa = fileService.uploadArquivo(capa);

        Filme filme = new Filme();
        filme.setTitulo(dto.getTitulo());
        filme.setDescricao(dto.getDescricao());
        filme.setAnoLancamento(dto.getAnoLancamento());
        filme.setDuracaoMinutos(dto.getDuracaoMinutos());
        filme.setTrailerUrlYoutube(dto.getTrailerUrlYoutube());
        filme.setCapaUrlMinio(nomeCapa);

        return filmeRepository.save(filme);
    }
}