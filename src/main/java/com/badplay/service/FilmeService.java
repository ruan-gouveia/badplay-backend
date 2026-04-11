package com.badplay.service;

import com.badplay.dto.FilmeRequestDTO;
import com.badplay.entity.Filme;
import com.badplay.entity.Genero;
import com.badplay.repository.FilmeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final FileService fileService;
    private final GeneroService generoService;

    public FilmeService(FilmeRepository filmeRepository, FileService fileService, GeneroService generoService) {
        this.filmeRepository = filmeRepository;
        this.fileService = fileService;
        this.generoService = generoService;
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

        if (dto.getGenerosIds() != null && !dto.getGenerosIds().isEmpty()) {
            List<Genero> generosEncontrados = generoService.buscarPorIds(dto.getGenerosIds());
            filme.setGeneros(generosEncontrados);
        }
        return filmeRepository.save(filme);
    }
}