package com.badplay.dto;

import com.badplay.entity.Assinatura;
import com.badplay.entity.StatusAssinatura;
import java.time.LocalDate;

public class AssinaturaResponseDTO {
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private StatusAssinatura status;

    private String planoTipo;

    private String usuarioNome;
    private String usuarioEmail;

    public AssinaturaResponseDTO(Assinatura assinatura) {
        this.id = assinatura.getId();
        this.dataInicio = assinatura.getDataInicio();
        this.dataFim = assinatura.getDataFim();
        this.status = assinatura.getStatus();
        this.planoTipo = assinatura.getPlano().getTipo().name();
        this.usuarioNome = assinatura.getUsuario().getNome();
        this.usuarioEmail = assinatura.getUsuario().getEmail();
    }

    public Long getId() { return id; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public StatusAssinatura getStatus() { return status; }
    public String getPlanoTipo() { return planoTipo; }
    public String getUsuarioNome() { return usuarioNome; }
    public String getUsuarioEmail() { return usuarioEmail; }
}