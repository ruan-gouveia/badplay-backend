package com.badplay.service;

import com.badplay.dto.EstatisticasResponseDTO;
import com.badplay.repository.FilmeRepository;
import com.badplay.repository.HistoricoReproducaoRepository;
import com.badplay.repository.SerieRepository;
import com.badplay.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class EstatisticasService {

    private final FilmeRepository filmeRepository;
    private final SerieRepository serieRepository;
    private final UsuarioRepository usuarioRepository;
    private final HistoricoReproducaoRepository historicoRepository;

    public EstatisticasService(FilmeRepository filmeRepository,
                               SerieRepository serieRepository,
                               UsuarioRepository usuarioRepository,
                               HistoricoReproducaoRepository historicoRepository) {
        this.filmeRepository = filmeRepository;
        this.serieRepository = serieRepository;
        this.usuarioRepository = usuarioRepository;
        this.historicoRepository = historicoRepository;
    }

    public EstatisticasResponseDTO buscarEstatisticas() {
        long totalFilmes = filmeRepository.count();
        long totalSeries = serieRepository.count();
        long totalUsuarios = usuarioRepository.count();
        long reproducoesHoje = historicoRepository.countReproducoesHoje();

        return new EstatisticasResponseDTO(totalFilmes, totalSeries, totalUsuarios, reproducoesHoje);
    }
}