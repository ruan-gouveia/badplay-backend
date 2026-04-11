package com.badplay.service;

import com.badplay.dto.EpisodioRequestDTO;
import com.badplay.dto.SerieRequestDTO;
import com.badplay.dto.TemporadaRequestDTO;
import com.badplay.entity.Episodio;
import com.badplay.entity.Serie;
import com.badplay.entity.Temporada;
import com.badplay.repository.SerieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SerieService {

    private final SerieRepository serieRepository;
    private final FileService fileService;

    public SerieService(SerieRepository serieRepository, FileService fileService) {
        this.serieRepository = serieRepository;
        this.fileService = fileService;
    }

    @Transactional
    public Serie salvar(SerieRequestDTO dto, MultipartFile capa) {
        String nomeCapa = fileService.uploadArquivo(capa);

        Serie serie = new Serie();
        serie.setTitulo(dto.getTitulo());
        serie.setDescricao(dto.getDescricao());
        serie.setAnoLancamento(dto.getAnoLancamento());
        serie.setCapaUrlMinio(nomeCapa);

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

        return serieRepository.save(serie);
    }
}