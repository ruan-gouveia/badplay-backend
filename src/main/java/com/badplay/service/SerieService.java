package com.badplay.service;

import com.badplay.dto.EpisodioRequestDTO;
import com.badplay.dto.SerieRequestDTO;
import com.badplay.dto.TemporadaRequestDTO;
import com.badplay.entity.Episodio;
import com.badplay.entity.Genero;
import com.badplay.entity.Serie;
import com.badplay.entity.Temporada;
import com.badplay.repository.AvaliacaoRepository;
import com.badplay.repository.HistoricoReproducaoRepository;
import com.badplay.repository.ListaDesejoRepository;
import com.badplay.repository.SerieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SerieService {

    private final SerieRepository serieRepository;
    private final FileService fileService;
    private final GeneroService generoService;
    private final HistoricoReproducaoRepository historicoRepository;
    private final AvaliacaoRepository avaliacaoRepository;
    private final ListaDesejoRepository listaDesejoRepository;

    public SerieService(SerieRepository serieRepository,
                        FileService fileService,
                        GeneroService generoService,
                        HistoricoReproducaoRepository historicoRepository,
                        AvaliacaoRepository avaliacaoRepository,
                        ListaDesejoRepository listaDesejoRepository) {
        this.serieRepository = serieRepository;
        this.fileService = fileService;
        this.generoService = generoService;
        this.historicoRepository = historicoRepository;
        this.avaliacaoRepository = avaliacaoRepository;
        this.listaDesejoRepository = listaDesejoRepository;
    }

    public List<Serie> listarTodos() {
        return serieRepository.findAll();
    }

    public Serie buscarPorId(Long id) {
        return serieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Série não encontrada"));
    }

    @Transactional
    public Serie salvar(SerieRequestDTO dto, MultipartFile capa) {
        String nomeCapa = fileService.uploadArquivo(capa);

        Serie serie = new Serie();
        serie.setTitulo(dto.getTitulo());
        serie.setDescricao(dto.getDescricao());
        serie.setAnoLancamento(dto.getAnoLancamento());
        serie.setCapaUrlMinio(nomeCapa);
        serie.setTrailerUrlYoutube(dto.getTrailerUrlYoutube());

        if (dto.getPlanoMinimo() != null) {
            serie.setPlanoMinimo(dto.getPlanoMinimo());
        }

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

        return serieRepository.save(serie);
    }

    @Transactional
    public Serie atualizar(Long id, SerieRequestDTO dto, MultipartFile capa) {
        Serie serie = serieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Série não encontrada"));

        serie.setTitulo(dto.getTitulo());
        serie.setDescricao(dto.getDescricao());
        serie.setAnoLancamento(dto.getAnoLancamento());
        serie.setTrailerUrlYoutube(dto.getTrailerUrlYoutube());

        if (dto.getPlanoMinimo() != null) {
            serie.setPlanoMinimo(dto.getPlanoMinimo());
        }

        if (dto.getGenerosIds() != null && !dto.getGenerosIds().isEmpty()) {
            serie.setGeneros(generoService.buscarPorIds(dto.getGenerosIds()));
        }

        if (capa != null && !capa.isEmpty()) {
            String nomeCapa = fileService.uploadArquivo(capa);
            serie.setCapaUrlMinio(nomeCapa);
        }

        if (dto.getTemporadas() != null) {
            serie.getTemporadas().clear();
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

    @Transactional
    public void deletar(Long id) {
        if (!serieRepository.existsById(id)) {
            throw new RuntimeException("Série não encontrada com ID: " + id);
        }

        historicoRepository.deleteByConteudoId(id);
        avaliacaoRepository.deleteByConteudoId(id);
        listaDesejoRepository.deleteConteudoFromAllListas(id);

        serieRepository.deleteById(id);
    }
}