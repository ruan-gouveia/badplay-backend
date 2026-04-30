package com.badplay.service;

import com.badplay.dto.EpisodioRequestDTO;
import com.badplay.dto.SerieRequestDTO;
import com.badplay.dto.TemporadaRequestDTO;
import com.badplay.entity.Episodio;
import com.badplay.entity.Genero; // Importe Genero
import com.badplay.entity.Serie;
import com.badplay.entity.Temporada;
import com.badplay.repository.SerieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List; // Importe List

@Service
public class SerieService {

    private final SerieRepository serieRepository;
    private final FileService fileService;
    private final GeneroService generoService;

    public SerieService(SerieRepository serieRepository, FileService fileService, GeneroService generoService) {
        this.serieRepository = serieRepository;
        this.fileService = fileService;
        this.generoService = generoService;
    }

    @Transactional
    public Serie salvar(SerieRequestDTO dto, MultipartFile capa) {
        String nomeCapa = fileService.uploadArquivo(capa);

        Serie serie = new Serie();
        serie.setTitulo(dto.getTitulo());
        serie.setDescricao(dto.getDescricao());
        serie.setAnoLancamento(dto.getAnoLancamento());
        serie.setCapaUrlMinio(nomeCapa);

        if (dto.getGenerosIds() != null && !dto.getGenerosIds().isEmpty()) {
            List<Genero> generosEncontrados = generoService.buscarPorIds(dto.getGenerosIds());
            serie.setGeneros(generosEncontrados);
        }

        if (dto.getTemporadas() != null) {
            for (TemporadaRequestDTO tempDto : dto.getTemporadas()) {
                Temporada temporada = new Temporada();
                temporada.setNumeroTemporada(tempDto.getNumeroTemporada());
                temporada.setSerie(serie);
                if (tempDto.getEpisodios() != null) {
                    for (EpisodioRequestDTO epDto : tempDto.getEpisodios()) {
                        Episodio episodio = new Episodio();
                        episodio.setNome(epDto.getNome());
                        episodio.setNumeroEpisodio(epDto.getNumeroEpisodio());
                        episodio.setDuracaoMinutos(epDto.getDuracaoMinutos());
                        episodio.setTrailerUrlYoutube(epDto.getTrailerUrlYoutube());
                        episodio.setTemporada(temporada);
                        temporada.getEpisodios().add(episodio);
                    }
                }
                serie.getTemporadas().add(temporada);
            }
        }

        if (dto.getPlanoMinimo() != null) {
            serie.setPlanoMinimo(dto.getPlanoMinimo());
        }

        return serieRepository.save(serie);
    }
}