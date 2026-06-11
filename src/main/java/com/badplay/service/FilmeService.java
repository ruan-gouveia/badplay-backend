package com.badplay.service;

import com.badplay.dto.FilmeRequestDTO;
import com.badplay.entity.Filme;
import com.badplay.entity.Genero;
import com.badplay.repository.AvaliacaoRepository;
import com.badplay.repository.FilmeRepository;
import com.badplay.repository.HistoricoReproducaoRepository;
import com.badplay.repository.ListaDesejoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final FileService fileService;
    private final GeneroService generoService;

    // Repositórios para a exclusão em cascata (Delete Absoluto)
    private final HistoricoReproducaoRepository historicoRepository;
    private final AvaliacaoRepository avaliacaoRepository;
    private final ListaDesejoRepository listaDesejoRepository;

    public FilmeService(FilmeRepository filmeRepository,
                        FileService fileService,
                        GeneroService generoService,
                        HistoricoReproducaoRepository historicoRepository,
                        AvaliacaoRepository avaliacaoRepository,
                        ListaDesejoRepository listaDesejoRepository) {
        this.filmeRepository = filmeRepository;
        this.fileService = fileService;
        this.generoService = generoService;
        this.historicoRepository = historicoRepository;
        this.avaliacaoRepository = avaliacaoRepository;
        this.listaDesejoRepository = listaDesejoRepository;
    }

    // GUARDA A LISTA EM MEMÓRIA CACHE
    @Cacheable("filmes")
    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    public Filme buscarPorId(Long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
    }

    // SE SALVAR UM NOVO, APAGA O CACHE PARA FORÇAR ATUALIZAÇÃO
    @CacheEvict(value = "filmes", allEntries = true)
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

        if (dto.getPlanoMinimo() != null) {
            filme.setPlanoMinimo(dto.getPlanoMinimo());
        }

        if (dto.getGenerosIds() != null && !dto.getGenerosIds().isEmpty()) {
            List<Genero> generosEncontrados = generoService.buscarPorIds(dto.getGenerosIds());
            filme.setGeneros(generosEncontrados);
        }

        return filmeRepository.save(filme);
    }

    @CacheEvict(value = "filmes", allEntries = true)
    @Transactional
    public Filme atualizar(Long id, FilmeRequestDTO dto, MultipartFile capa) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        filme.setTitulo(dto.getTitulo());
        filme.setDescricao(dto.getDescricao());
        filme.setAnoLancamento(dto.getAnoLancamento());
        filme.setDuracaoMinutos(dto.getDuracaoMinutos());
        filme.setTrailerUrlYoutube(dto.getTrailerUrlYoutube());

        if (dto.getPlanoMinimo() != null) {
            filme.setPlanoMinimo(dto.getPlanoMinimo());
        }

        if (dto.getGenerosIds() != null && !dto.getGenerosIds().isEmpty()) {
            filme.setGeneros(generoService.buscarPorIds(dto.getGenerosIds()));
        }

        // Atualiza a imagem apenas se o Admin enviar uma nova
        if (capa != null && !capa.isEmpty()) {
            String nomeCapa = fileService.uploadArquivo(capa);
            filme.setCapaUrlMinio(nomeCapa);
        }

        return filmeRepository.save(filme);
    }

    @CacheEvict(value = "filmes", allEntries = true)
    @Transactional
    public void deletar(Long id) {
        if (!filmeRepository.existsById(id)) {
            throw new RuntimeException("Filme não encontrado com ID: " + id);
        }

        // Limpa os vestígios do filme para evitar erro de integridade (Foreign Key)
        historicoRepository.deleteByConteudoId(id);
        avaliacaoRepository.deleteByConteudoId(id);
        listaDesejoRepository.deleteConteudoFromAllListas(id);

        filmeRepository.deleteById(id);
    }
}